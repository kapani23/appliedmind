package com.appliedmind.entity.user;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.appliedmind.utils.enums.UserOnboardingChannelEnum;
import com.appliedmind.utils.enums.UserOnboardingStatusEnum;

@Entity
@Table(name = "USER_ONBOARDING")
public class UserOnboardingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ONBOARDING_ID")
	private Long userOnboardingId;

	@OneToOne(targetEntity = UserProfileEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "PROFILE_ID", referencedColumnName = "PROFILE_ID")
	private UserProfileEntity userProfileEntity;

	// Browser or Mobile
	@Enumerated(EnumType.STRING)
	@Column(name = "ONBOARDING_CHANNEL")
	private UserOnboardingChannelEnum onboardingChannel;

	// code sent, verified etc
	@Enumerated(EnumType.STRING)
	@Column(name = "ONBOARDING_STATUS")
	private UserOnboardingStatusEnum onboardingStatus;

	@Column(name = "CREATED_TIME", nullable = false)
	private LocalDateTime createdTime;

	@Column(name = "UPDATED_TIME", nullable = false)
	private LocalDateTime updatedTime;

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

	public UserOnboardingStatusEnum getOnboardingStatus() {
		return onboardingStatus;
	}

	public void setOnboardingStatus(UserOnboardingStatusEnum onboardingStatus) {
		this.onboardingStatus = onboardingStatus;
	}

	public UserOnboardingChannelEnum getOnboardingChannel() {
		return onboardingChannel;
	}

	public void setOnboardingChannel(UserOnboardingChannelEnum webOrMobile) {
		this.onboardingChannel = webOrMobile;
	}

	public Long getUserOnboardingId() {
		return userOnboardingId;
	}

	public void setUserOnboardingId(Long userOnboardingId) {
		this.userOnboardingId = userOnboardingId;
	}

	@Override
	public String toString() {
		return "UserOnboardingEntity [userOnboardingId=" + userOnboardingId + ", userProfileEntity=" + userProfileEntity
				+ ", onboardingStatus=" + onboardingStatus + ", onboardingChannel=" + onboardingChannel
				+ ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + "]";
	}

}
