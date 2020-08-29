package com.appliedmind.entity.user;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_PROFILE")
public class UserProfileEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROFILE_ID")
	private Long userProfileId;

	@OneToOne(mappedBy = "userProfileEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private UserAddressEntity userAddressEntity;

	@OneToOne(mappedBy = "userProfileEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private UserOnboardingEntity userOnboardingEntity;

	@OneToMany(mappedBy = "userProfileEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UserServicesEntity> userServicesCategoryEntity;

	@OneToOne(mappedBy = "userProfileEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private UserDeviceMetadataEntity userDeviceMetadataEntity;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Column(name = "EMAIL", unique = true)
	private String email;

	@Column(name = "PHONE", unique = true)
	private String phone;

	@Lob
	@Column(name = "IMAGE")
	private Blob userImage;

	@Column(name = "CREATED_TIME", nullable = false)
	private LocalDateTime createdTime;

	@Column(name = "UPDATED_TIME", nullable = false)
	private LocalDateTime updatedTime;

	public Long getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(Long userProfileId) {
		this.userProfileId = userProfileId;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public UserAddressEntity getUserAddressEntity() {
		return userAddressEntity;
	}

	public void setUserAddressEntity(UserAddressEntity userAddressEntity) {
		this.userAddressEntity = userAddressEntity;
	}

	public UserOnboardingEntity getUserOnboardingEntity() {
		return userOnboardingEntity;
	}

	public void setUserOnboardingEntity(UserOnboardingEntity userOnboardingEntity) {
		this.userOnboardingEntity = userOnboardingEntity;
	}

	public List<UserServicesEntity> getUserServicesCategoryEntity() {
		return userServicesCategoryEntity;
	}

	public void setUserServicesCategoryEntity(List<UserServicesEntity> userServicesCategoryEntity) {
		this.userServicesCategoryEntity = userServicesCategoryEntity;
	}

	public UserDeviceMetadataEntity getUserDeviceMetadataEntity() {
		return userDeviceMetadataEntity;
	}

	public void setUserDeviceMetadataEntity(UserDeviceMetadataEntity userDeviceMetadataEntity) {
		this.userDeviceMetadataEntity = userDeviceMetadataEntity;
	}

	public Blob getUserImage() {
		return userImage;
	}

	public void setUserImage(Blob image) {
		this.userImage = image;
	}
}
