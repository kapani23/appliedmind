package com.appliedmind.dto.user;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.appliedmind.validation.ValidEmail;
import com.appliedmind.validation.ValidPhone;

public class ProfileVerificationRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Token is blank")
	private String token;

	@ValidEmail
	private String email;

	@ValidPhone
	private String phone;

	private DeviceMetada userDeviceMetada;

	public DeviceMetada getUserDeviceMetada() {
		return userDeviceMetada;
	}

	public void setUserDeviceMetada(DeviceMetada userDeviceMetada) {
		this.userDeviceMetada = userDeviceMetada;
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

}
