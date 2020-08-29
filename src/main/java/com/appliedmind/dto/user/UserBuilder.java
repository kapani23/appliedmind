package com.appliedmind.dto.user;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.appliedmind.entity.user.UserDeviceMetadataEntity;
import com.appliedmind.entity.user.UserOnboardingEntity;
import com.appliedmind.entity.user.UserProfileEntity;
import com.appliedmind.utils.enums.UserOnboardingStatusEnum;

@Component
public class UserBuilder {

	public UserProfileEntity createNewUserProfileEntity(ProfileCreationRequest creationRequest) {

		LocalDateTime time = LocalDateTime.now();

		UserProfileEntity profileEntity = new UserProfileEntity();
		profileEntity.setFirstName(creationRequest.getFirstName());
		profileEntity.setLastName(creationRequest.getLastName());
		profileEntity.setEmail(creationRequest.getEmail());
		profileEntity.setPhone(creationRequest.getPhone());
		profileEntity.setCreatedTime(time);
		profileEntity.setUpdatedTime(time);

		UserOnboardingEntity onboardingEntity = new UserOnboardingEntity();
		onboardingEntity.setCreatedTime(time);
		onboardingEntity.setUpdatedTime(time);
		onboardingEntity.setOnboardingChannel(creationRequest.getUserDeviceMetada().getDeviceTypeEnum());
		onboardingEntity.setOnboardingStatus(UserOnboardingStatusEnum.VERIFICATION_CODE_SENT);
		onboardingEntity.setUserProfileEntity(profileEntity);
		profileEntity.setUserOnboardingEntity(onboardingEntity);

		UserDeviceMetadataEntity deviceEntity = new UserDeviceMetadataEntity();
		deviceEntity.setIpAddress(creationRequest.getUserDeviceMetada().getIpAdress());
		deviceEntity.setUserAgent(creationRequest.getUserDeviceMetada().getUserAgent());
		deviceEntity.setDeviceType(creationRequest.getUserDeviceMetada().getDeviceType());
		deviceEntity.setDevicePlatform(creationRequest.getUserDeviceMetada().getDevicePlatform());
		deviceEntity.setCreatedTime(time);
		deviceEntity.setUpdatedTime(time);
		deviceEntity.setUserProfileEntity(profileEntity);
		profileEntity.setUserDeviceMetadataEntity(deviceEntity);

		return profileEntity;
	}

	public ProfileCreationResponse buildProfileCreationResponse(ProfileCreationRequest request, String code,
			String message) {

		ProfileCreationResponse response = new ProfileCreationResponse();

		response.setFirstName(request.getFirstName());
		response.setLastName(request.getLastName());
		response.setEmail(request.getEmail());
		response.setPhone(request.getPhone());

		response.setResponseCode(code);
		response.setResponseDescription(message);

		return response;
	}

	public LoginResponse buildLoginResponse(UserProfileEntity profileEntity, ValidationResponse validationResponse) {

		LoginResponse response = new LoginResponse();

		if (profileEntity != null) {
			response.setFirstName(profileEntity.getFirstName());
			response.setLastName(profileEntity.getLastName());
			response.setEmail(profileEntity.getEmail());
			response.setPhone(profileEntity.getPhone());
		}

		if (validationResponse != null) {
			response.setResponseCode(validationResponse.getResponseCode());
			response.setResponseDescription(validationResponse.getResponseDescription());
		}

		return response;
	}
}
