package com.appliedmind.utils;

import static java.util.Objects.nonNull;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Component;

@Component
public class DeviceMetadataUtils {

	public String getDeviceType(Device device) {

		String deviceType = "Unknown";
		if (device.isNormal()) {
			deviceType = "Browser";
		} else if (device.isMobile()) {
			deviceType = "Mobile";
		} else if (device.isTablet()) {
			deviceType = "Tablet";
		}
		return deviceType;
	}

	// iOS or Android
	public String getDevicePlatform(Device device) {
		String platform = device.getDevicePlatform().name();
		if (platform.equalsIgnoreCase("UNKNOWN")) {
			platform = "Browser";
		}
		return platform;
	}

	public String getIpAddress(HttpServletRequest request) {
		String clientIp = null;
		String clientXForwardedForIp = request.getHeader("x-forwarded-for");
		if (nonNull(clientXForwardedForIp)) {
			clientIp = clientXForwardedForIp.split(" *, *")[0];
		} else {
			clientIp = request.getRemoteAddr();
		}

		return clientIp;
	}

	public String getUserAgent(HttpServletRequest request) {
		return request.getHeader("User-Agent");
	}

}
