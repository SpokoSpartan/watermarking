package com.watermark.watermarkapi.controllers;

import com.watermark.watermarkapi.services.AnalyzeWatermarkService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analyze")
public class AnalyzeController {

	private final AnalyzeWatermarkService analyzeService;

	public AnalyzeController(AnalyzeWatermarkService analyzeService) {
		this.analyzeService = analyzeService;
	}

	@PostMapping("/watermark-level")
	public Integer getWatermarkLevel(@RequestParam("imageUrl") String imageUrl) {
		return analyzeService.getWatermarkLevel(imageUrl);
	}

}
