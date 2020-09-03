package com.appliedmind.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appliedmind.dto.user.DeviceMetada;
import com.appliedmind.dto.user.LoginRequest;
import com.appliedmind.dto.user.LoginResponse;
import com.appliedmind.dto.user.ProfileCreationRequest;
import com.appliedmind.dto.user.ProfileCreationResponse;
import com.appliedmind.dto.user.ProfileVerificationRequest;
import com.appliedmind.dto.user.ProfileVerificationResponse;
import com.appliedmind.dto.user.ProviderRegistrationRequest;
import com.appliedmind.service.UserService;
import com.appliedmind.utils.DeviceMetadataUtils;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private DeviceMetadataUtils deviceUtils;

	// Create profile and generate OTP
	@PostMapping(path = "/createUserProfile", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProfileCreationResponse> createUserProfile(
			@RequestBody @Valid ProfileCreationRequest createRequest, Device device,
			HttpServletRequest servletRequest) {

		DeviceMetada userDeviceMetada = buildUserDeviceMetada(device, servletRequest);
		createRequest.setUserDeviceMetada(userDeviceMetada);

		return ResponseEntity.ok(this.userService.createUserProfile(createRequest));

	}

	// Verify user profile against generated OTP
	@PostMapping(path = "/verifyUserProfile", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProfileVerificationResponse> verifyUserProfile(
			@RequestBody @Valid ProfileVerificationRequest verifyRequest, HttpServletRequest httpRequest) {

		return ResponseEntity.ok(this.userService.verifyUserProfile(verifyRequest));
	}

	// Verify the user login
	@PostMapping(path = "/verifyUserLogin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoginResponse> verifyUserLogin(@RequestBody @Valid LoginRequest loginRequest,
			HttpServletRequest httpRequest) {

		return ResponseEntity.ok(this.userService.verifyUserLogin(loginRequest));
	}

	// Verify the user login
	@PostMapping(path = "/updateUserServices", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> updateUserServices(
			@RequestBody @Valid ProviderRegistrationRequest providerRegRequest) {
		return ResponseEntity.ok(this.userService.updateUserServices(providerRegRequest));
	}

	private DeviceMetada buildUserDeviceMetada(Device device, HttpServletRequest servletRequest) {

		DeviceMetada metada = new DeviceMetada();
		metada.setIpAdress(deviceUtils.getIpAddress(servletRequest));
		metada.setUserAgent(deviceUtils.getUserAgent(servletRequest));
		metada.setDeviceType(deviceUtils.getDeviceType(device));
		metada.setDevicePlatform(deviceUtils.getDevicePlatform(device));

		return metada;
	}
}
