package com.watermark.watermarkapi.controllers;

import com.watermark.watermarkapi.domains.ImageUrl;
import com.watermark.watermarkapi.domains.WatermarkUrl;
import com.watermark.watermarkapi.services.ImageService;
import com.watermark.watermarkapi.services.WatermarkService;
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
	private final WatermarkService watermarkService;

	public ImageController(ImageService imageService, WatermarkService watermarkService) {

		this.imageService = imageService;
		this.watermarkService = watermarkService;
	}

	@PostMapping("/upload")
	public ImageUrl uploadImage(@RequestParam("image") MultipartFile image) {
		return imageService.uploadImage(image);
	}

	@PostMapping("/watermark")
	public WatermarkUrl watermarkImage(@RequestParam("algorithm") String algorithm,
								@RequestParam("imageId") Integer imageId) {
		return watermarkService.watermarkImage(imageId, algorithm);
	}
}
