package com.watermark.watermarkapi.services;

import com.watermark.watermarkapi.domains.ImageUrl;
import com.watermark.watermarkapi.entities.Picture;
import com.watermark.watermarkapi.entities.User;
import com.watermark.watermarkapi.exceptions.ValidationException;
import com.watermark.watermarkapi.repositories.PictureRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Service
public class ImageService {

	private final PictureRepository pictureRepository;
	private final UserService userService;

	private final List<String> supportedTypes = Arrays.asList("image/bmp", "image/png");

	@Value("${file-repository.max-file-size}")
	private long maxFileSize;

	@Value("${file-repository.save-directory}")
	private String saveDirectory;

	public ImageService(PictureRepository pictureRepository, UserService userService) {
		this.pictureRepository = pictureRepository;
		this.userService = userService;
	}

	public ImageUrl uploadImage(MultipartFile image) {
		validateFile(image);

		String ext = FilenameUtils.getExtension(image.getOriginalFilename());
		byte[] imageAsByte = getImageAsBytes(image);
		String url = saveOnDisc(imageAsByte, ext);

		User user = userService.getLoggedInUser();
		Picture picture = new Picture(url, user);
		picture.setName(image.getOriginalFilename());
		pictureRepository.saveAndFlush(picture);
		Picture pictureFromDB = pictureRepository.findByPictureUrl(url);

		return new ImageUrl(url, url, user.getId(), pictureFromDB.getPictureId());
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

	private byte[] getImageAsBytes(MultipartFile file) {
		try {
			return file.getBytes();
		} catch (IOException e) {
			throw new ValidationException("Malformed file.");
		}
	}

	private String saveOnDisc(byte[] imageAsByte, String format) {
		try {
			return tryToSaveOnDisc(imageAsByte, format);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private String tryToSaveOnDisc(byte[] imageAsByte, String format) throws IOException {
		ByteArrayInputStream bis = new ByteArrayInputStream(imageAsByte);
		BufferedImage bImage2 = ImageIO.read(bis);
		String name = UUID.randomUUID().toString() + "." + format;
		ImageIO.write(bImage2, format, new File(saveDirectory + name));
		return "http://localhost:8080/image/get?name=" + name;
	}

	public byte[] getImage(String name) {
		try {
			return tryToReadImage(name);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private byte[] tryToReadImage(String name) throws IOException {
		BufferedImage bImage = ImageIO.read(new File(saveDirectory + name));
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		String ext = FilenameUtils.getExtension(name);
		ImageIO.write(bImage, ext, bos);
		return bos.toByteArray();
	}

}
