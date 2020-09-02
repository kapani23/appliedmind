package com.appliedmind.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.appliedmind.dto.token.TokenCreationRequest;
import com.appliedmind.dto.token.TokenVerificationRequest;
import com.appliedmind.dto.token.TokenVerificationResponse;
import com.appliedmind.dto.user.LoginRequest;
import com.appliedmind.dto.user.LoginResponse;
import com.appliedmind.dto.user.ProfileCreationRequest;
import com.appliedmind.dto.user.ProfileCreationResponse;
import com.appliedmind.dto.user.ProfileVerificationRequest;
import com.appliedmind.dto.user.ProfileVerificationResponse;
import com.appliedmind.dto.user.ProviderRegistrationRequest;
import com.appliedmind.dto.user.ServiceRequest;
import com.appliedmind.dto.user.SkillRequest;
import com.appliedmind.dto.user.UserBuilder;
import com.appliedmind.dto.user.ValidationResponse;
import com.appliedmind.entity.user.UserOnboardingEntity;
import com.appliedmind.entity.user.UserProfileEntity;
import com.appliedmind.entity.user.UserServicesEntity;
import com.appliedmind.entity.user.UserSkillsEntity;
import com.appliedmind.repository.UserRepository;
import com.appliedmind.service.TokenService;
import com.appliedmind.service.UserService;
import com.appliedmind.utils.ResponseCode;
import com.appliedmind.utils.enums.UserOnboardingStatusEnum;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserBuilder userBuilder;

	@Override
	public ProfileCreationResponse createUserProfile(ProfileCreationRequest creationRequest) {

		ProfileCreationResponse response = null;

		try {

			String email = creationRequest.getEmail();
			String phone = creationRequest.getPhone();

			// Scenario 1 - User is already registered but not verified then re-send OTP
			ValidationResponse validationResponse = validateUserProfile(email, phone);
			if (validationResponse != null) {
				this.tokenService.createAndSendToken(new TokenCreationRequest(email, phone));
				response = userBuilder.buildProfileCreationResponse(creationRequest,
						validationResponse.getResponseCode(), validationResponse.getResponseDescription());
				return response;
			}

			// Scenario 2 - New User registration
			// Step 1 - Generate OTP, Save into DB and Send it to email/sms
			this.tokenService.createAndSendToken(new TokenCreationRequest(email, phone));
			// Step 2 - Create User Profile, Create Onboarding, Create Device Metadata
			UserProfileEntity profileEntity = userBuilder.createNewUserProfileEntity(creationRequest);
			// Step 3 - Create New user profile response
			response = userBuilder.buildProfileCreationResponse(creationRequest,
					ResponseCode.USER_REGISTERED_NOT_VERFIRIED.getCode(),
					ResponseCode.USER_REGISTERED_NOT_VERFIRIED.getDescription());

			this.userRepository.save(profileEntity);

		} catch (ResponseStatusException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Error occured while registering user - " + ex.getMessage());
		}
		return response;
	}

	@Override
	public ProfileVerificationResponse verifyUserProfile(ProfileVerificationRequest verificationRequest) {

		ProfileVerificationResponse response = new ProfileVerificationResponse();

		String email = verificationRequest.getEmail();
		String phone = verificationRequest.getPhone();

		UserProfileEntity profileEntity = null;

		if (StringUtils.isNotBlank(phone)) {
			profileEntity = userRepository.findByPhone(phone);
		} else if (StringUtils.isNotBlank(email)) {
			profileEntity = userRepository.findByEmail(email);
		} else {
			throw new RuntimeException("Cannot happen. We always want to have a search query of phone or email");
		}

		if (profileEntity == null) {
			response.setResponseCode(ResponseCode.USER_NOT_REGISTERED.getCode());
			response.setResponseDescription(ResponseCode.USER_NOT_REGISTERED.getDescription());
			return response;
		}

		String token = verificationRequest.getToken();

		TokenVerificationResponse tokenResponse = this.tokenService
				.verifyToken(new TokenVerificationRequest(token, email, phone));

		if (tokenResponse.isTokenVerified()) {

			LocalDateTime currentDateTime = LocalDateTime.now();

			UserOnboardingEntity onboardingEntity = profileEntity.getUserOnboardingEntity();
			onboardingEntity.setOnboardingStatus(UserOnboardingStatusEnum.VERIFICATION_DONE);
			onboardingEntity.setUpdatedTime(currentDateTime);
			onboardingEntity.setUserProfileEntity(profileEntity);

			profileEntity.setUpdatedTime(currentDateTime);
			profileEntity.setUserOnboardingEntity(onboardingEntity);

			this.userRepository.save(profileEntity);

			response.setResponseCode(ResponseCode.TOKEN_VERIFIED.getCode());
			response.setResponseDescription(ResponseCode.TOKEN_VERIFIED.getDescription());

		} else if (tokenResponse.isTokenExpired()) {
			response.setResponseCode(ResponseCode.TOKEN_EXPIRED.getCode());
			response.setResponseDescription(ResponseCode.TOKEN_EXPIRED.getDescription());
		} else if (tokenResponse.isTokenInvalid()) {
			response.setResponseCode(ResponseCode.TOKEN_INVALID.getCode());
			response.setResponseDescription(ResponseCode.TOKEN_INVALID.getDescription());
		}

		return response;
	}

	@Override
	public LoginResponse verifyUserLogin(LoginRequest loginRequest) {

		boolean isUserRegistered = false;
		boolean isUserVerified = false;

		String email = loginRequest.getEmail();
		String phone = loginRequest.getPhone();

		UserProfileEntity profileEntity = null;

		if (StringUtils.isNotBlank(email)) { // Validate email
			profileEntity = userRepository.findByEmail(email);
			if (profileEntity != null) {
				isUserRegistered = true;
				UserOnboardingEntity onboardingEntity = profileEntity.getUserOnboardingEntity();
				if (onboardingEntity != null) {
					isUserVerified = UserOnboardingStatusEnum.VERIFICATION_DONE
							.equals(onboardingEntity.getOnboardingStatus());
				}
			}
		} else if (StringUtils.isNotBlank(phone)) { // Validate phone
			profileEntity = userRepository.findByPhone(phone);
			if (profileEntity != null) {
				isUserRegistered = true;
				UserOnboardingEntity onboardingEntity = profileEntity.getUserOnboardingEntity();
				if (onboardingEntity != null && !isUserVerified) {
					isUserVerified = UserOnboardingStatusEnum.VERIFICATION_DONE
							.equals(onboardingEntity.getOnboardingStatus());
				}
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Missing Login credential, Please provide either Email or Phone");
		}

		ValidationResponse response = null;

		if (isUserRegistered) {
			if (isUserVerified) { // Scenario 1 - User is registered and verified
				response = new ValidationResponse(ResponseCode.USER_AUTHENTICATED.getCode(),
						ResponseCode.USER_AUTHENTICATED.getDescription());
			} else { // Scenario 2 - User is registered but not verified so sending OTP
				this.tokenService.createAndSendToken(new TokenCreationRequest(email, phone));
				response = new ValidationResponse(ResponseCode.USER_REGISTERED_NOT_VERFIRIED.getCode(),
						ResponseCode.USER_REGISTERED_NOT_VERFIRIED.getDescription());
			}
		} else { // Scenario 3 - User is not registered
			response = new ValidationResponse(ResponseCode.USER_NOT_REGISTERED.getCode(),
					ResponseCode.USER_NOT_REGISTERED.getDescription());
		}

		return userBuilder.buildLoginResponse(profileEntity, response);
	}

	@Override
	public boolean updateUserAddress() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUserServices(ProviderRegistrationRequest providerRegRequest) {

		String phone = null;
		String email = null;

		UserProfileEntity profileEntity = null;

		if (StringUtils.isNotBlank(phone)) {
			profileEntity = userRepository.findByPhone(phone);
		} else if (StringUtils.isNotBlank(email)) {
			profileEntity = userRepository.findByEmail(email);
		} else {
			throw new RuntimeException("Cannot happen. We always want to have a search query of phone or email");
		}

		List<UserServicesEntity> userServices = profileEntity.getUserServicesEntity();

		if (userServices == null) {
			userServices = new ArrayList<>();
		}

		List<ServiceRequest> services = providerRegRequest.getServices();

		for (ServiceRequest serviceRequest : services) {

			// Service which is equal to Category i.e. Teaching / Cooking / Wellness &
			UserServicesEntity servicesEntity = new UserServicesEntity();
			servicesEntity.setAboutYourSelf("");
			servicesEntity.setAwardsRecognition("");
			servicesEntity.setCertification(serviceRequest.getServiceExperienceSummary().getCertifications().get(0));
			servicesEntity.setExperience("");
			servicesEntity.setInstituteName("");
			servicesEntity.setQualification(serviceRequest.getServiceExperienceSummary().getQualification());
			servicesEntity.setServiceName("");
			servicesEntity.setUserProfileEntity(profileEntity);

			List<SkillRequest> skills = serviceRequest.getSkills();

			for (SkillRequest skill : skills) {

				List<UserSkillsEntity> userSkillsEntityList = new ArrayList<>();

				// Skills i.e. Math Grade 6, Math Grade 7
				UserSkillsEntity userSkillEntity = new UserSkillsEntity();
				userSkillEntity.setInstituteName("");
				userSkillEntity.setQualification("");
				userSkillEntity.setSkillCertification("");
				userSkillEntity.setSkillExp("");
				userSkillEntity.setSkillName("");
				userSkillEntity.setUserServicesEntity(servicesEntity);
				
			}

		}

		

		return false;
	}

	@Override
	public boolean updateUserSkills() {
		// TODO Auto-generated method stub
		return false;
	}

	private ValidationResponse validateUserProfile(String email, String phone) {

		boolean isEmailRegistered = false;
		boolean isPhoneRegistered = false;
		boolean isUserVerified = false;

		if (StringUtils.isNotBlank(email)) { // Validate email
			UserProfileEntity entityByEmail = userRepository.findByEmail(email);
			if (entityByEmail != null) {
				isEmailRegistered = true;
				UserOnboardingEntity onboardingEntity = entityByEmail.getUserOnboardingEntity();
				if (onboardingEntity != null) {
					isUserVerified = UserOnboardingStatusEnum.VERIFICATION_DONE
							.equals(onboardingEntity.getOnboardingStatus());
				}
			}
		}

		if (StringUtils.isNotBlank(phone)) { // Validate phone
			UserProfileEntity entityByPhone = userRepository.findByPhone(phone);
			if (entityByPhone != null) {
				isPhoneRegistered = true;
				UserOnboardingEntity onboardingEntity = entityByPhone.getUserOnboardingEntity();
				if (onboardingEntity != null && !isUserVerified) {
					isUserVerified = UserOnboardingStatusEnum.VERIFICATION_DONE
							.equals(onboardingEntity.getOnboardingStatus());
				}
			}
		}

		ValidationResponse response = null;

		if (!isUserVerified && (isEmailRegistered || isPhoneRegistered)) {
			// User is registered but not verified
			response = new ValidationResponse(ResponseCode.USER_REGISTERED_NOT_VERFIRIED.getCode(),
					ResponseCode.USER_REGISTERED_NOT_VERFIRIED.getDescription());
		} else if (isUserVerified && (isEmailRegistered || isPhoneRegistered)) {
			// User is already registered & verified or someone using already registered
			// credentials
			response = new ValidationResponse(ResponseCode.USER_RETRYING_TO_REGISTER_WITH_SAME_DETAILS.getCode(),
					ResponseCode.USER_RETRYING_TO_REGISTER_WITH_SAME_DETAILS.getDescription());
		}

		return response;
	}
}