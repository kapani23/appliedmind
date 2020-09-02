package com.appliedmind.dto.user;

import java.util.List;

public class ProviderRegistrationRequest {

	private ProviderBio providerBio = null;

	private List<ServiceRequest> services = null;

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
