package com.appliedmind.search;

public class ServicesSearchableIdentity {

	private ServiceCategory serviceCategory = null;
	private String id = null;
	private String value = null;
	private String microDescription = null;
	private String miniDescription = null;
	private String description = null;

	public ServicesSearchableIdentity(String id, String value, ServiceCategory serviceCategory) {
		this.id = id;
		this.value = value;
		this.serviceCategory = serviceCategory;
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

	public void setDescription(String descirpiton) {
		this.description = descirpiton;
	}

	// delegates
	public String getCategoryDescription() {
		return serviceCategory.getDescription();
	}

	// delegates
	public String getCategoryId() {
		return serviceCategory.getId();
	}

	// delegates
	public String getCategoryValue() {
		return serviceCategory.getValue();
	}

	@Override
	public String toString() {
		return "ServicesSearchableIdentity [serviceCategory=" + serviceCategory + ", id=" + id + ", value=" + value
				+ "]";
	}

}
