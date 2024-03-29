package com.appliedmind.entity.user;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_SKILLS")
public class UserSkillsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SKILL_ID")
	private Long userSkillId;

	@ManyToOne(targetEntity = UserServicesEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_SERVICES_ID", referencedColumnName = "USER_SERVICES_ID")
	private UserServicesEntity userServicesEntity;

	// Java, Piano, GCP, French, Maths, Hindi
	@Column(name = "SKILL_NAME")
	private String skillName;

	// 10 years
	@Column(name = "SKILL_EXPERIENCE")
	private String skillExp;

	//
	@Column(name = "SKILL_QUALIFICATION")
	private String qualification;

	@Lob
	@Column(name = "SKILL_CERTIFICATION")
	// User will upload the cert so that we can show to consumer
	private String skillCertification;

	@Lob
	@Column(name = "AWARDS_RECOGNITION")
	// User will upload the awards/recognition so that we can show to consumer
	private String awardsRecognition;

	@Column(name = "INSTITUTE_NAME")
	private String instituteName;

	@Column(name = "CREATED_TIME", nullable = false)
	private LocalDateTime createdTime;

	@Column(name = "UPDATED_TIME", nullable = false)
	private LocalDateTime updatedTime;

	public Long getUserSkillId() {
		return userSkillId;
	}

	public void setUserSkillId(Long userSkillId) {
		this.userSkillId = userSkillId;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	public LocalDateTime getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(LocalDateTime updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getSkillExp() {
		return skillExp;
	}

	public void setSkillExp(String skillExp) {
		this.skillExp = skillExp;
	}

	public String getSkillCertification() {
		return skillCertification;
	}

	public void setSkillCertification(String skillCertification) {
		this.skillCertification = skillCertification;
	}

	public UserServicesEntity getUserServicesEntity() {
		return userServicesEntity;
	}

	public void setUserServicesEntity(UserServicesEntity userServicesEntity) {
		this.userServicesEntity = userServicesEntity;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

}
