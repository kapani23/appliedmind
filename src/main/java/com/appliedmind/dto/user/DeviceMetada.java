package com.appliedmind.dto.user;

import java.io.Serializable;

import com.appliedmind.utils.enums.UserOnboardingChannelEnum;

public class DeviceMetada implements Serializable {

	private static final long serialVersionUID = 1L;

	// Browser, Mobile, Tablet
	private String deviceType;

	// Browser, IOS, Android
	private String devicePlatform;

	// User Agent
	private String userAgent;

	private String ipAdress;

	public String getIpAdress() {
		return ipAdress;
	}

	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public UserOnboardingChannelEnum getDeviceTypeEnum() {
		UserOnboardingChannelEnum deviceEnum = UserOnboardingChannelEnum.UNKNOWN;
		if ("Browser".equalsIgnoreCase(this.deviceType)) {
			deviceEnum = UserOnboardingChannelEnum.WEB;
		} else if ("Mobile".equalsIgnoreCase(this.deviceType)) {
			deviceEnum = UserOnboardingChannelEnum.MOBILE;
		} else if ("Tablet".equalsIgnoreCase(this.deviceType)) {
			deviceEnum = UserOnboardingChannelEnum.TABLET;
		}
		return deviceEnum;
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
