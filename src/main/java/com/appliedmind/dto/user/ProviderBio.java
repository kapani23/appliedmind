package com.appliedmind.dto.user;

public class ProviderBio {

	private String funFactsAboutYourself = null;

	private String formalEducation = null;

	public String getFunFactsAboutYourself() {
		return funFactsAboutYourself;
	}

	public void setFunFactsAboutYourself(String funFactsAboutYourself) {
		this.funFactsAboutYourself = funFactsAboutYourself;
	}

	public String getFormalEducation() {
		return formalEducation;
	}

	public void setFormalEducation(String formalEducation) {
		this.formalEducation = formalEducation;
	}

	@Override
	public String toString() {
		return "ProviderBio [funFactsAboutYourself=" + funFactsAboutYourself + ", formalEducation=" + formalEducation
				+ "]";
	}

}
