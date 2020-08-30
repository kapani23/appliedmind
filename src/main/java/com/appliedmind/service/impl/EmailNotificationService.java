package com.appliedmind.service.impl;

import java.lang.invoke.MethodHandles;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.appliedmind.service.NotificationService;

@Service("email")
public class EmailNotificationService implements NotificationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	MessageSource messageSource;

	private JavaMailSender javaMailSender;

	public EmailNotificationService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Override
	public boolean sendMessage(String phoneOrEmail, String verificationCode) {

		boolean isMessageSent = false;

		try {

			MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {

				public void prepare(MimeMessage mimeMessage) throws Exception {
					mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(phoneOrEmail));
					mimeMessage.setFrom(new InternetAddress("appliedmind.marketplace@gmail.com", "AppliedMind"));
					mimeMessage.setSubject("AppliedMind OTP");
					String[] params = new String[] { verificationCode };
					String mediaMessage = messageSource.getMessage("otp.message", params,
							LocaleContextHolder.getLocale());
					mimeMessage.setText(mediaMessage);
				}
			};
			this.javaMailSender.send(messagePreparator);
			isMessageSent = true;
			LOGGER.debug("Email sent on " + phoneOrEmail + " with OTP - " + verificationCode);
		} catch (Exception ex) {
			LOGGER.debug("Email failed for - " + phoneOrEmail);
		}
		return isMessageSent;
	}
}
