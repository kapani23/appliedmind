package com.appliedmind.dto.user;

import java.util.List;

public class SkillExperienceRequest {

	private List<String> certifications = null;

	private String qualification = null;

	private String whyThisSkill = null;

	private String yearOfExperience = null;

	private String noOfStudentsTaught = null;

	public List<String> getCertifications() {
		return certifications;
	}

	public void setCertifications(List<String> certifications) {
		this.certifications = certifications;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getWhyThisSkill() {
		return whyThisSkill;
	}

	public void setWhyThisSkill(String whyThisSkill) {
		this.whyThisSkill = whyThisSkill;
	}

	public String getYearOfExperience() {
		return yearOfExperience;
	}

	public void setYearOfExperience(String yearOfExperience) {
		this.yearOfExperience = yearOfExperience;
	}

	public String getNoOfStudentsTaught() {
		return noOfStudentsTaught;
	}

	public void setNoOfStudentsTaught(String noOfStudentsTaught) {
		this.noOfStudentsTaught = noOfStudentsTaught;
	}

	@Override
	public String toString() {
		return "SkillExperienceRequest [certifications=" + certifications + ", qualification=" + qualification
				+ ", whyThisSkill=" + whyThisSkill + ", yearOfExperience=" + yearOfExperience + ", noOfStudentsTaught="
				+ noOfStudentsTaught + "]";
	}

}
