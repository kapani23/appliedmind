package com.appliedmind.dto.user;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.appliedmind.validation.ValidEmail;
import com.appliedmind.validation.ValidPhone;

public class ProfileCreationRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "First Name is blank")
	private String firstName;

	@NotBlank(message = "Last Name is blank")
	private String lastName;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UserRegistrationRequest [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phone=" + phone + "]";
	}
}
