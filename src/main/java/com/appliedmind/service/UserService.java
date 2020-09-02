package com.appliedmind.service;

import com.appliedmind.dto.user.LoginRequest;
import com.appliedmind.dto.user.LoginResponse;
import com.appliedmind.dto.user.ProfileCreationRequest;
import com.appliedmind.dto.user.ProfileCreationResponse;
import com.appliedmind.dto.user.ProfileVerificationRequest;
import com.appliedmind.dto.user.ProfileVerificationResponse;
import com.appliedmind.dto.user.ProviderRegistrationRequest;

public interface UserService {

	public ProfileCreationResponse createUserProfile(ProfileCreationRequest creationRequest);

	public ProfileVerificationResponse verifyUserProfile(ProfileVerificationRequest verificationRequest);

	public LoginResponse verifyUserLogin(LoginRequest loginRequest);

	public boolean updateUserAddress();

	public boolean updateUserServices(ProviderRegistrationRequest providerRegRequest);

	public boolean updateUserSkills();
}
