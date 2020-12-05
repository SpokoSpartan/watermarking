package com.watermark.watermarkapi.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AnalyzeWatermarkService {

	private final RestTemplate client;

	public AnalyzeWatermarkService(RestTemplate client) {
		this.client = client;
	}

	public Integer getWatermarkLevel(String imageUrl) {
		ResponseEntity<String> response = client.postForEntity("http://localhost:5000/watermark-level" +
				"?url=" + imageUrl, null, String.class);
		return Integer.parseInt(response.getBody());
	}

}
