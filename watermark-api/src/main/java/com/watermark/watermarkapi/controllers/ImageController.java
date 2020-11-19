package com.watermark.watermarkapi.controllers;

import com.watermark.watermarkapi.domains.ImageUrl;
import com.watermark.watermarkapi.services.ImageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/image")
@CrossOrigin(origins = "http://localhost:4200", methods = {POST, GET, PUT, OPTIONS, DELETE, HEAD})
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
