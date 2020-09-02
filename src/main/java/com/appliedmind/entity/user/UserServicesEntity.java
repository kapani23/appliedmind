package com.appliedmind.entity.user;

import java.time.LocalDateTime;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_SERVICES")
public class UserServicesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_SERVICES_ID")
	private Long userServiceId;

	@ManyToOne(targetEntity = UserProfileEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "PROFILE_ID", referencedColumnName = "PROFILE_ID")
	private UserProfileEntity userProfileEntity;

	@OneToMany(mappedBy = "userServicesEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UserSkillsEntity> userSkillsEntity;

	// Teaching, Wellness & Health, Music, Cooking
	@OneToOne(mappedBy = "userServicesEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private ServiceCategoryEntity serviceCategoryEntity;

	// Software Developer, Lead Engineer etc
	// Teacher in top school in Pune etc
	@Column(name = "ABOUT_YOUR_SELF")
	private String aboutYourSelf;

	// French Teacher, English Teacher, Chef, Musician
	@Column(name = "SERVICE_NAME")
	private String serviceName;

	@Column(name = "QUALIFICATION")
	private String qualification;

	@Column(name = "INSTITUTE_NAME")
	private String instituteName;

	@Column(name = "EXPERIENCE")
	private String experience;

	@Lob
	@Column(name = "CERTIFICATION")
	// User will upload the cert so that we can show to consumer
	private String certification;

	@Lob
	@Column(name = "AWARDS_RECOGNITION")
	// User will upload the awards/recognition so that we can show to consumer
	private String awardsRecognition;

	@Column(name = "CREATED_TIME", nullable = false)
	private LocalDateTime createdTime;

	@Column(name = "UPDATED_TIME", nullable = false)
	private LocalDateTime updatedTime;

	public Long getUserServiceId() {
		return userServiceId;
	}

	public void setUserServiceId(Long userServiceId) {
		this.userServiceId = userServiceId;
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

	public UserProfileEntity getUserProfileEntity() {
		return userProfileEntity;
	}

	public void setUserProfileEntity(UserProfileEntity userProfileEntity) {
		this.userProfileEntity = userProfileEntity;
	}

	public List<UserSkillsEntity> getUserSkillsEntity() {
		return userSkillsEntity;
	}

	public void setUserSkillsEntity(List<UserSkillsEntity> userSkillsEntity) {
		this.userSkillsEntity = userSkillsEntity;
	}

	public ServiceCategoryEntity getServiceCategoryEntity() {
		return serviceCategoryEntity;
	}

	public void setServiceCategoryEntity(ServiceCategoryEntity serviceCategoryEntity) {
		this.serviceCategoryEntity = serviceCategoryEntity;
	}

	public String getAboutYourSelf() {
		return aboutYourSelf;
	}

	public void setAboutYourSelf(String aboutYourSelf) {
		this.aboutYourSelf = aboutYourSelf;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
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

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public String getAwardsRecognition() {
		return awardsRecognition;
	}

	public void setAwardsRecognition(String awardsRecognition) {
		this.awardsRecognition = awardsRecognition;
	}
}
