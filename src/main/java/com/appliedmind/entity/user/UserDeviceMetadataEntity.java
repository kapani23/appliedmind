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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_DEVICE_METADATA")
public class UserDeviceMetadataEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DEVICE_METADATA_ID")
	private Long userDeviceMetadataId;

	@OneToOne(targetEntity = UserProfileEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "PROFILE_ID", referencedColumnName = "PROFILE_ID")
	private UserProfileEntity userProfileEntity;

	// Browser, Mobile, Tablet
	@Column(name = "DEVICE_TYPE")
	private String deviceType;

	// Browser, IOS, Android
	@Column(name = "DEVICE_PLATFORM")
	private String devicePlatform;

	// User Agent
	@Column(name = "USER_AGENT")
	private String userAgent;

	@Column(name = "IP_ADDRESS")
	private String ipAddress;

	@Column(name = "CREATED_TIME", nullable = false)
	private LocalDateTime createdTime;

	@Column(name = "UPDATED_TIME", nullable = false)
	private LocalDateTime updatedTime;

	public Long getUserDeviceMetadataId() {
		return userDeviceMetadataId;
	}

	public void setUserDeviceMetadataId(Long userDeviceMetadataId) {
		this.userDeviceMetadataId = userDeviceMetadataId;
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

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDevicePlatform() {
		return devicePlatform;
	}

	public void setDevicePlatform(String devicePlatform) {
		this.devicePlatform = devicePlatform;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

}
