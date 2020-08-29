package com.appliedmind.service;

import com.appliedmind.dto.token.TokenCreationRequest;
import com.appliedmind.dto.token.TokenVerificationRequest;
import com.appliedmind.dto.token.TokenVerificationResponse;

public interface TokenService {

	public String createToken(TokenCreationRequest tokenRequest);

	public String createAndSendToken(TokenCreationRequest tokenRequest);

	public TokenVerificationResponse verifyToken(TokenVerificationRequest tokenRequest);
}
