package com.appliedmind.dto.user;

import java.io.Serializable;

public class ProfileCreationResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String firstName;

	private String lastName;

	private String email;

	private String phone;

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
		return "UserLoginRequest [email=" + email + ", phone=" + phone + "]";
	}

}
