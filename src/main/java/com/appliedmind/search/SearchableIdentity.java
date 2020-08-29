package com.appliedmind.search;

public class SearchableIdentity {

	private String id = null;
	private String value = null;
	private String category = null;
	private String description = null;

	public SearchableIdentity(String id, String description) {
		this.id = id;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "SearchableIdentity [id=" + id + ", description=" + description + "]";
	}

}
