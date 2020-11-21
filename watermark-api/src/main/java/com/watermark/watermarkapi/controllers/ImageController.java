package com.watermark.watermarkapi.controllers;

import com.watermark.watermarkapi.domains.ImageUrl;
import com.watermark.watermarkapi.services.ImageService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
public class ImageController {

	private final ImageService imageService;

	public ImageController(ImageService imageService) {
		this.imageService = imageService;
	}

	@PostMapping("/upload")
	public ImageUrl uploadImage(@RequestParam("image") MultipartFile image) {
		return imageService.uploadImage(image);
	}

}
