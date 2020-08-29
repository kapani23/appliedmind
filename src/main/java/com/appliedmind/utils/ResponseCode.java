package com.appliedmind.utils;

public enum ResponseCode {

	// 42X - For Registration
	USER_NOT_REGISTERED("421", "User is not registered in the system. Please do signup"),

	USER_REGISTERED_NOT_VERFIRIED("422", "User is registered but OTP/Token verification is pending"),

	USER_RETRYING_TO_REGISTER_WITH_SAME_DETAILS("423",
			"User is re-registering with same details. Challenge for token."),

	// // 43X - For Auth
	USER_AUTHENTICATED("430", "User is verified and authenticated. Move to landing page"),

	USER_AUTHENTICATED_BUT_NEW_DEVICE("431",
			"User verified and authenticated but from a new device. Challenge for token."),

	USER_VERIFIED_BUT_TOKEN_COOKIE_EXPIRED("432",
			"User is verified but cookie expired after 168 hrs. Challenge for token"),

	// 44X - For Token
	TOKEN_CREATED("440", "Token is created"),

	TOKEN_VERIFIED("441", "Token is verified"),

	TOKEN_EXPIRED("442", "Token is expired"),

	TOKEN_INVALID("443", "Token is invalid");

	private String code = null;
	private String description = null;

	private ResponseCode(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

}
