package com.appliedmind.dto.user;

import java.io.Serializable;

import com.appliedmind.validation.ValidEmail;
import com.appliedmind.validation.ValidPhone;

public class LoginRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@ValidEmail
	private String email;

	@ValidPhone
	private String phone;

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
		return "UserLoginRequest [email=" + email + ", phone=" + phone + "]";
	}

}
