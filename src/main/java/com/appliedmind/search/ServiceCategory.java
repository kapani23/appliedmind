package com.appliedmind.search;

public class ServiceCategory {

	private String id = null;
	private String value = null;
	private String microDescription = null;
	private String miniDescription = null;
	private String description = null;

	public ServiceCategory(String id, String value) {
		this.id = id;
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public String getMicroDescription() {
		return microDescription;
	}

	public void setMicroDescription(String microDescription) {
		this.microDescription = microDescription;
	}

	public String getMiniDescription() {
		return miniDescription;
	}

	public void setMiniDescription(String miniDescription) {
		this.miniDescription = miniDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String descripiton) {
		this.description = descripiton;
	}

	@Override
	public String toString() {
		return "ServiceCategory [id=" + id + ", value=" + value + "]";
	}

}
