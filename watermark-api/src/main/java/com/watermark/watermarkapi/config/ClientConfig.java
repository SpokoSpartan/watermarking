package com.watermark.watermarkapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfig {

	@Bean
	public RestTemplate prepareClient() {
		return new RestTemplate();
	}

}
