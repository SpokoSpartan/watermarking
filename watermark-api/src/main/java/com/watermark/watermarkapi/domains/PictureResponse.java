package com.watermark.watermarkapi.domains;

public class PictureResponse {

	private Integer pictureId;
	private String url;
	private String watermarkedUrl;

	public Integer getPictureId() {
		return pictureId;
	}

	public void setPictureId(Integer pictureId) {
		this.pictureId = pictureId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWatermarkedUrl() {
		return watermarkedUrl;
	}

	public void setWatermarkedUrl(String watermarkedUrl) {
		this.watermarkedUrl = watermarkedUrl;
	}

}
