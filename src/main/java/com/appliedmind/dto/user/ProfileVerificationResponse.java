package com.appliedmind.dto.user;

import java.io.Serializable;

public class ProfileVerificationResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String responseCode = null;

	private String responseDescription = null;

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	@Override
	public String toString() {
		return "ProfileVerificationResponse [responseCode=" + responseCode + ", responseDescription="
				+ responseDescription + "]";
	}

}
