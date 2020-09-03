package com.appliedmind.service;

import com.appliedmind.dto.user.ValidationResponse;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -4167754992208924259L;
	
	private ValidationResponse validationResponse = null;

	public ServiceException(ValidationResponse validationResponse) {
		super();
		this.validationResponse = validationResponse;
	}

	public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public String getResponseCode() {
		return validationResponse.getResponseCode();
	}

	public String getResponseDescription() {
		return validationResponse.getResponseDescription();
	}
	
	public ValidationResponse getValidationResponse() {
		return this.validationResponse;
	}

}
