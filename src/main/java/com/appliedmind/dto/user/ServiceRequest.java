package com.appliedmind.dto.user;

import java.util.List;

public class ServiceRequest {

	private String serviceId = null;

	private ServiceExperienceRequest serviceExperienceSummary = null;

	private List<SkillRequest> skills = null;

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public ServiceExperienceRequest getServiceExperienceSummary() {
		return serviceExperienceSummary;
	}

	public void setServiceExperienceSummary(ServiceExperienceRequest serviceExperienceSummary) {
		this.serviceExperienceSummary = serviceExperienceSummary;
	}

	public List<SkillRequest> getSkills() {
		return skills;
	}

	public void setSkills(List<SkillRequest> skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		return "ServiceRequest [serviceId=" + serviceId + ", serviceExperienceSummary=" + serviceExperienceSummary
				+ ", skills=" + skills + "]";
	}

}
