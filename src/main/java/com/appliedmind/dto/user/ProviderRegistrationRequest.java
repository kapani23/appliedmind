package com.appliedmind.dto.user;

import java.util.List;

import com.appliedmind.validation.ValidEmail;
import com.appliedmind.validation.ValidPhone;

public class ProviderRegistrationRequest {

	@ValidEmail
	private String email;

	@ValidPhone
	private String phone;

	private ProviderBio providerBio = null;

	private List<ServiceRequest> services = null;

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

	public ProviderBio getProviderBio() {
		return providerBio;
	}

	public void setProviderBio(ProviderBio providerBio) {
		this.providerBio = providerBio;
	}

	public List<ServiceRequest> getServices() {
		return services;
	}

	public void setServices(List<ServiceRequest> services) {
		this.services = services;
	}

	@Override
	public String toString() {
		return "ProviderRegistrationRequest [providerBio=" + providerBio + ", services=" + services + "]";
	}

}
