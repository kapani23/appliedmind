package com.appliedmind.search;

public class ServiceEngagementTerm {

	private String id = null;
	private String engagementTermType = null;

	public ServiceEngagementTerm(String id, String engagementTermType) {
		this.id = id;
		this.engagementTermType = engagementTermType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEngagementTermType() {
		return engagementTermType;
	}

	public void setEngagementTermType(String engagementTermType) {
		this.engagementTermType = engagementTermType;
	}

}
