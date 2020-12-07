package com.watermark.watermarkapi.controllers;

import com.watermark.watermarkapi.domains.ImageUrl;
import com.watermark.watermarkapi.domains.WatermarkUrl;
import com.watermark.watermarkapi.services.ImageService;
import com.watermark.watermarkapi.services.WatermarkService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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

	@GetMapping(value = "/get", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody
	byte[] getImage(@RequestParam("name") String name) {
		return imageService.getImage(name);
	}

	@PostMapping("/watermark")
	public WatermarkUrl watermarkImage(@RequestParam("algorithm") String algorithm,
									   @RequestParam("imageId") Integer imageId) {
		return watermarkService.watermarkImage(imageId, algorithm);
	}

	@GetMapping("/watermark/{algorithm}/{imageId}")
	@ResponseBody
	public WatermarkUrl watermarkImageById(@PathVariable String algorithm,
									   @PathVariable Integer imageId) {
		return watermarkService.watermarkImage(imageId, algorithm);
	}
}
