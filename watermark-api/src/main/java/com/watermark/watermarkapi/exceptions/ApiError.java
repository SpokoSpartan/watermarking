package com.watermark.watermarkapi.exceptions;


import org.springframework.http.HttpStatus;

public class ApiError {

	private String message;
	private String code;

	public ApiError(String message, String code) {
		this.message = message;
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public String getCode() {
		return code;
	}

}
