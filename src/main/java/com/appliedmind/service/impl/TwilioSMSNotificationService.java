package com.appliedmind.service.impl;

import java.lang.invoke.MethodHandles;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.appliedmind.service.NotificationService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service("sms")
public class TwilioSMSNotificationService implements NotificationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Value("${com.twilio.auth.sid}")
	private String userName = null;

	@Value("${com.twilio.auth.token}")
	private String password = null;

	@Value("${com.twilio.phone.number}")
	private String fromNumber = null;

	@Autowired
	MessageSource messageSource;

	@PostConstruct
	public void init() {
		Twilio.init(userName, password);
	}

	@Override
	public boolean sendMessage(String phoneOrEmail, String verificationCode) {

		boolean isMessageSent = false;

		try {

			PhoneNumber smsTo = new PhoneNumber(phoneOrEmail);
			PhoneNumber smsFrom = new PhoneNumber(fromNumber);
			String[] params = new String[] { verificationCode };
			String mediaMessage = messageSource.getMessage("otp.message", params, LocaleContextHolder.getLocale());
			Message.creator(smsTo, smsFrom, mediaMessage).create();
			isMessageSent = true;
			LOGGER.debug("SMS sent on " + phoneOrEmail + " with OTP - " + verificationCode);
		} catch (Exception ex) {
			LOGGER.debug("SMS failed for - " + phoneOrEmail);
		}
		return isMessageSent;
	}
}
