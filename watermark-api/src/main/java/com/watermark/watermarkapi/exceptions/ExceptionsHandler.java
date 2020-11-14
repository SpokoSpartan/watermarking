package com.watermark.watermarkapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ApiError> catchValidationError(ValidationException exception, WebRequest request) {
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ApiError(exception.getMessage(), "VALIDATION_EXCEPTION"));
	}

}
