package com.watermark.watermarkapi.domains;

public class ImageUrl {

	private final String url;
	private final String secureUrl;
	private final Integer userId;
	private final Integer pictureId;

	public ImageUrl(String url, String secureUrl, Integer userId, Integer pictureId) {
		this.url = url;
		this.secureUrl = secureUrl;
		this.userId = userId;
		this.pictureId = pictureId;
	}

	public Integer getPictureId() { return pictureId; }

	public Integer getUserId() { return userId; }

	public String getUrl() {
		return url;
	}

	public String getSecureUrl() {
		return secureUrl;
	}
}
