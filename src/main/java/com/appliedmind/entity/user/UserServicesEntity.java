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

	// Teacher, Musician
	@OneToOne(mappedBy = "userServicesEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private ServiceCategoryEntity serviceCategoryEntity;

	// Software Developer, Lead Engineer etc
	// Teacher in top school in Pune etc
	@Column(name = "ABOUT_YOUR_SELF")
	private String aboutYourSelf;

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
}
