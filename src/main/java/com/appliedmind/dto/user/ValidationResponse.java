package com.appliedmind.dto.user;

public class ValidationResponse {

	private String responseCode;

	private String responseDescription;

	public ValidationResponse(String responseCode, String responseDescription) {
		super();
		this.responseCode = responseCode;
		this.responseDescription = responseDescription;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public String getResponseDescription() {
		return responseDescription;
	}
}
