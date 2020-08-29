package com.appliedmind.dto.search;

public class BaseInfoResponse {

	private String id = null;
	private String value = null;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "BaseInfoResponse [id=" + id + ", value=" + value + "]";
	}

}
