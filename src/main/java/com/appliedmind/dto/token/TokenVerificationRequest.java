package com.appliedmind.dto.token;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.appliedmind.validation.ValidEmail;
import com.appliedmind.validation.ValidPhone;

public class TokenVerificationRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Token is blank")
	private String token;

	@ValidEmail
	private String email;

	@ValidPhone
	private String phone;

	public TokenVerificationRequest() {
		super();
	}

	public TokenVerificationRequest(String token, String email, String phone) {
		super();
		this.token = token;
		this.email = email;
		this.phone = phone;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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
		return "VerificationTokenRequest [token=" + token + ", email=" + email + ", phone=" + phone + "]";
	}

}
