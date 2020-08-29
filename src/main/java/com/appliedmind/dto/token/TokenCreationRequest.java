package com.appliedmind.dto.token;

import java.io.Serializable;

import com.appliedmind.validation.ValidEmail;
import com.appliedmind.validation.ValidPhone;

public class TokenCreationRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@ValidEmail
	private String email;

	@ValidPhone
	private String phone;

	public TokenCreationRequest() {
		super();
	}

	public TokenCreationRequest(String email, String phone) {
		super();
		this.email = email;
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "VerificationTokenRegenerateRequest [email=" + email + ", phone=" + phone + "]";
	}

}
