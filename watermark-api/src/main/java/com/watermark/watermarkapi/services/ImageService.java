package com.watermark.watermarkapi.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.watermark.watermarkapi.domains.ImageUrl;
import com.watermark.watermarkapi.exceptions.ValidationException;
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

	public ImageUrl uploadImage(MultipartFile image) {
		validateFile(image);
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", cloudName,
				"api_key", apiKey,
				"api_secret", apiSecret));
		Map<?, ?> res = uploadToCloud(image, cloudinary);
		return new ImageUrl((String) res.get("url"), (String) res.get("secure_url"));
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
