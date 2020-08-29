package com.appliedmind.service.impl;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appliedmind.dto.token.TokenCreationRequest;
import com.appliedmind.dto.token.TokenVerificationRequest;
import com.appliedmind.dto.token.TokenVerificationResponse;
import com.appliedmind.entity.token.VerifcationTokenEntity;
import com.appliedmind.repository.TokenRepository;
import com.appliedmind.service.NotificationService;
import com.appliedmind.service.TokenService;
import com.appliedmind.utils.ResponseCode;
import com.appliedmind.utils.enums.UserVerificationChannelEnum;
import com.appliedmind.utils.enums.UserVerificationTokenStatusEnum;

@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private TokenRepository repository;

	@Autowired
	private Map<String, NotificationService> notificationService;

	@Override
	public String createToken(TokenCreationRequest tokenRequest) {

		String email = tokenRequest.getEmail();
		String phone = tokenRequest.getPhone();

		// Active token already exist so returning the same active token
		VerifcationTokenEntity tokenEntity = this.findActiveTokenByEmail(email);
		if (tokenEntity == null) {
			tokenEntity = this.findActiveTokenByPhone(phone);
		}

		// No active token exist so generate new token
		if (tokenEntity == null) {

			SecureRandom random = new SecureRandom();
			String token = String.format("%06d", random.nextInt(1000000));

			// Save token for both email and phone
			if (StringUtils.isNotBlank(email)) {
				tokenEntity = new VerifcationTokenEntity(UserVerificationChannelEnum.EMAIL, email, token);
				this.repository.save(tokenEntity);
			}

			if (StringUtils.isNotBlank(phone)) {
				tokenEntity = new VerifcationTokenEntity(UserVerificationChannelEnum.PHONE, phone, token);
				this.repository.save(tokenEntity);
			}
		}

		return tokenEntity.getToken();
	}

	@Override
	public String createAndSendToken(TokenCreationRequest tokenRequest) {

		// create otp
		String token = this.createToken(tokenRequest);

		// send otp on sms
		if (StringUtils.isNotBlank(tokenRequest.getPhone())) {
			this.notificationService.get("sms").sendMessage(tokenRequest.getPhone(), token);
		}

		// send otp on mail
		if (StringUtils.isNotBlank(tokenRequest.getEmail())) {
			this.notificationService.get("email").sendMessage(tokenRequest.getEmail(), token);
		}
		return token;
	}

	@Override
	public TokenVerificationResponse verifyToken(TokenVerificationRequest tokenRequest) {

		TokenVerificationResponse response = null;
		boolean isTokenValid = false;

		String email = tokenRequest.getEmail();
		String phone = tokenRequest.getPhone();

		VerifcationTokenEntity tokenEntity = this.findActiveTokenByEmail(email);
		if (tokenEntity == null) {
			tokenEntity = this.findActiveTokenByPhone(phone);
		}

		if (tokenEntity != null) {
			String dbToken = tokenEntity.getToken();
			isTokenValid = dbToken.equals(tokenRequest.getToken());
			if (isTokenValid) {
				response = new TokenVerificationResponse(ResponseCode.TOKEN_VERIFIED.getCode(),
						ResponseCode.TOKEN_VERIFIED.getDescription());
				// set the status to validated so that it can not be reused
				tokenEntity.setVerificationTokenStatus(UserVerificationTokenStatusEnum.VERIFICATION_CODE_VALIDATED);
				this.repository.save(tokenEntity);
			} else {
				response = new TokenVerificationResponse(ResponseCode.TOKEN_INVALID.getCode(),
						ResponseCode.TOKEN_INVALID.getDescription());
			}
		} else {
			response = new TokenVerificationResponse(ResponseCode.TOKEN_EXPIRED.getCode(),
					ResponseCode.TOKEN_EXPIRED.getDescription());
		}

		return response;
	}

	private VerifcationTokenEntity findActiveTokenByEmail(String email) {

		VerifcationTokenEntity tokenEntity = null;

		if (StringUtils.isNotBlank(email)) {
			tokenEntity = repository.findByTokenSentToAndTokenStatusAndExpiryTimeGreaterThan(email,
					UserVerificationTokenStatusEnum.VERIFICATION_CODE_GENERATED, LocalDateTime.now());
		}

		return tokenEntity;
	}

	private VerifcationTokenEntity findActiveTokenByPhone(String phone) {

		VerifcationTokenEntity tokenEntity = null;

		if (StringUtils.isNotBlank(phone)) {
			tokenEntity = repository.findByTokenSentToAndTokenStatusAndExpiryTimeGreaterThan(phone,
					UserVerificationTokenStatusEnum.VERIFICATION_CODE_GENERATED, LocalDateTime.now());

		}

		return tokenEntity;
	}
}
