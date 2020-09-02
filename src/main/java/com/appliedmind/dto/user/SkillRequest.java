package com.appliedmind.dto.user;

import java.util.List;

public class SkillRequest {

	private String skillId = null;

	private List<SkillEngagementFeeTerm> skillEngagementFeeTerms = null;

	private SkillExperienceRequest skillExperienceSummary = null;

	public String getSkillId() {
		return skillId;
	}

	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}

	public List<SkillEngagementFeeTerm> getSkillEngagementFeeTerms() {
		return skillEngagementFeeTerms;
	}

	public void setSkillEngagementFeeTerms(List<SkillEngagementFeeTerm> skillEngagementFeeTerms) {
		this.skillEngagementFeeTerms = skillEngagementFeeTerms;
	}

	public SkillExperienceRequest getSkillExperienceSummary() {
		return skillExperienceSummary;
	}

	public void setSkillExperienceSummary(SkillExperienceRequest skillExperienceSummary) {
		this.skillExperienceSummary = skillExperienceSummary;
	}

	@Override
	public String toString() {
		return "SkillRequest [skillId=" + skillId + ", skillEngagementFeeTerms=" + skillEngagementFeeTerms
				+ ", skillExperienceSummary=" + skillExperienceSummary + "]";
	}

}
