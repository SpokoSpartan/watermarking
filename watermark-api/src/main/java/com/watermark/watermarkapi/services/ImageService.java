package com.watermark.watermarkapi.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.watermark.watermarkapi.domains.ImageUrl;
import com.watermark.watermarkapi.entities.Picture;
import com.watermark.watermarkapi.entities.User;
import com.watermark.watermarkapi.exceptions.ValidationException;
import com.watermark.watermarkapi.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ImageService {

	private final PictureRepository pictureRepository;
	private final UserService userService;

	private final List<String> supportedTypes = Arrays.asList(
			MediaType.IMAGE_JPEG.toString(), MediaType.IMAGE_PNG.toString());

	@Value("${cloudinary.cloud-name}")
	private String cloudName;
	@Value("${cloudinary.api-key}")
	private String apiKey;
	@Value("${cloudinary.api-secret}")
	private String apiSecret;
	@Value("${cloudinary.max-file-size}")
	private long maxFileSize;

	public ImageService(PictureRepository pictureRepository, UserService userService) {
		this.pictureRepository = pictureRepository;
		this.userService = userService;
	}

	public ImageUrl uploadImage(MultipartFile image) {
		validateFile(image);
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", cloudName,
				"api_key", apiKey,
				"api_secret", apiSecret));
		Map<?, ?> res = uploadToCloud(image, cloudinary);
		String url = (String) res.get("url");
		String safeUrl = (String) res.get("secure_url");
		User user = userService.getLoggedInUser();
		Picture picture = new Picture(url, user);
		pictureRepository.saveAndFlush(picture);
		Picture pictureFromDB = pictureRepository.findByPictureUrl(url);
		ImageUrl imageUrl = new ImageUrl(url, safeUrl, user.getId(), pictureFromDB.getPictureId());
		return imageUrl;
	}

	private void validateFile(MultipartFile file) {
		if (file == null || file.isEmpty()) {
			throw new ValidationException("File is empty.");
		}

		if (file.getSize() > maxFileSize) {
			DecimalFormat decimalFormat = new DecimalFormat("###.#");
			throw new ValidationException("File size cannot exceed " +
					decimalFormat.format(maxFileSize / 1000000.0) + " MB.");
		}

		if (!supportedTypes.contains(file.getContentType()))
			throw new ValidationException("File type not supported.");
	}

	private Map<?, ?> uploadToCloud(MultipartFile image, Cloudinary cloudinary) {
		try {
			return cloudinary.uploader().upload(getImageAsBytes(image),
					ObjectUtils.asMap("resource_type", "auto"));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private byte[] getImageAsBytes(MultipartFile file) {
		try {
			return file.getBytes();
		} catch (IOException e) {
			throw new ValidationException("Malformed file.");
		}
	}
}
