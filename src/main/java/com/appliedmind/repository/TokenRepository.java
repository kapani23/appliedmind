package com.appliedmind.repository;

import java.time.LocalDateTime;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.appliedmind.entity.token.VerifcationTokenEntity;
import com.appliedmind.utils.enums.UserVerificationTokenStatusEnum;

@Repository
public interface TokenRepository extends CrudRepository<VerifcationTokenEntity, Long> {

	// channel may be phone or email
	public VerifcationTokenEntity findByTokenSentToAndTokenStatusAndExpiryTimeGreaterThan(String verificationSentTo,
			UserVerificationTokenStatusEnum verificationTokenStatus, LocalDateTime expiryTime);

	public VerifcationTokenEntity findByTokenSentTo(String tokenSentTo);

	public VerifcationTokenEntity findByTokenSentToAndTokenStatus(String tokenSentTo, String tokenStatus);
}
