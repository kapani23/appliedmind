package com.appliedmind.dto.token;

import java.io.Serializable;

import com.appliedmind.utils.ResponseCode;

public class TokenVerificationResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String responseCode = null;

	private String responseDescription = null;

	public TokenVerificationResponse(String responseCode, String responseDescription) {
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

	public boolean isTokenVerified() {
		return ResponseCode.TOKEN_VERIFIED.getCode().equals(this.responseCode);
	}

	public boolean isTokenExpired() {
		return ResponseCode.TOKEN_EXPIRED.getCode().equals(this.responseCode);
	}

	public boolean isTokenInvalid() {
		return ResponseCode.TOKEN_INVALID.getCode().equals(this.responseCode);
	}

}
