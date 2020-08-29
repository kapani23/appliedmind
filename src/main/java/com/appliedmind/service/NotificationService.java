package com.appliedmind.service;

public interface NotificationService {

	public boolean sendMessage(String phoneOrEmail, String verificationCode);

}
