package com.appliedmind.dto.user;

public class SkillEngagementFeeTerm {

	private String engagementType = null;

	private String engagementCost = null;

	public String getEngagementType() {
		return engagementType;
	}

	public void setEngagementType(String engagementType) {
		this.engagementType = engagementType;
	}

	public String getEngagementCost() {
		return engagementCost;
	}

	public void setEngagementCost(String engagementCost) {
		this.engagementCost = engagementCost;
	}

	@Override
	public String toString() {
		return "SkillEngagementFeeTerm [engagementType=" + engagementType + ", engagementCost=" + engagementCost + "]";
	}

}
