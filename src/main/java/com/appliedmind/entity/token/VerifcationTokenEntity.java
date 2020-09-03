package com.appliedmind.entity.token;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.appliedmind.utils.enums.UserVerificationChannelEnum;
import com.appliedmind.utils.enums.UserVerificationTokenStatusEnum;

@Entity
@Table(name = "VERIFICATION_TOKEN")
public class VerifcationTokenEntity {

	private static final long TOKEN_EXPIRATION_MIN = 5;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "VERIFICATION_ID")
	private Long verificationId;

	@Column(name = "TOKEN", nullable = false)
	private String token;

	@Column(name = "TOKEN_SENT_TO", nullable = false)
	private String tokenSentTo;

	// Phone or Email
	@Enumerated(EnumType.STRING)
	@Column(name = "VERIFICATION_CHANNEL", nullable = false)
	private UserVerificationChannelEnum verificationChannel;

	// Generated, Validated
	@Enumerated(EnumType.STRING)
	@Column(name = "TOKEN_STATUS", nullable = false)
	private UserVerificationTokenStatusEnum tokenStatus;

	@Column(name = "EXPIRY_TIME", nullable = false)
	private LocalDateTime expiryTime;

	@Column(name = "CREATED_TIME", nullable = false)
	private LocalDateTime createdTime;

	public VerifcationTokenEntity() {

	}

	public VerifcationTokenEntity(UserVerificationChannelEnum channel, String tokenSentTo, String token) {
		this.verificationChannel = channel;
		this.tokenSentTo = tokenSentTo;
		this.token = token;
		this.tokenStatus = UserVerificationTokenStatusEnum.VERIFICATION_CODE_GENERATED;
		LocalDateTime timeNow = LocalDateTime.now();
		this.createdTime = timeNow;
		LocalDateTime expiryTimeNow = timeNow.plusMinutes(TOKEN_EXPIRATION_MIN);
		this.expiryTime = expiryTimeNow;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public LocalDateTime getExpiryTime() {
		return expiryTime;
	}

	public UserVerificationChannelEnum getVerificationChannel() {
		return verificationChannel;
	}

	public String getTokenSentTo() {
		return tokenSentTo;
	}

	public UserVerificationTokenStatusEnum getTokenStatus() {
		return tokenStatus;
	}

	public void setVerificationTokenStatus(UserVerificationTokenStatusEnum tokenStatus) {
		this.tokenStatus = tokenStatus;
	}

	public String getToken() {
		return token;
	}

	@Override
	public String toString() {
		return "VerifcationTokenEntity [verificationId=" + verificationId + ", verificationChannel="
				+ verificationChannel + ", token=" + token + ", tokenStatus=" + tokenStatus + ", expiryTime="
				+ expiryTime + ", createdTime=" + createdTime + "]";
	}

}
