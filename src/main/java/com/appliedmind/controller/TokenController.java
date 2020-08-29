package com.appliedmind.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appliedmind.dto.token.TokenCreationRequest;
import com.appliedmind.dto.token.TokenVerificationRequest;
import com.appliedmind.dto.token.TokenVerificationResponse;
import com.appliedmind.service.TokenService;

@RestController
@RequestMapping("/token")
@CrossOrigin(origins = "http://localhost:8084")
public class TokenController {

	@Autowired
	private TokenService tokenService;

	@PostMapping(path = "/createToken", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createToken(@RequestBody @Valid TokenCreationRequest request) {

		return ResponseEntity.ok(tokenService.createToken(request));
	}

	@PostMapping(path = "/verifyToken", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TokenVerificationResponse> validateToken(
			@RequestBody @Valid TokenVerificationRequest request) {

		return ResponseEntity.ok(tokenService.verifyToken(request));
	}

}
