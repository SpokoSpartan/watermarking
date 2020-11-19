package com.watermark.watermarkapi.domains;

public class ImageUrl {

	private final String url;
	private final String secureUrl;

	public ImageUrl(String url, String secureUrl) {
		this.url = url;
		this.secureUrl = secureUrl;
	}

	public String getUrl() {
		return url;
	}

	public String getSecureUrl() {
		return secureUrl;
	}
}
