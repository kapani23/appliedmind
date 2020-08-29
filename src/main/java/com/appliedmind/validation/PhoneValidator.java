package com.appliedmind.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class PhoneValidator implements ConstraintValidator<ValidPhone, String> {

	private static final String PHONE_PATTERN = "^\\+(?:[0-9] ?){6,14}[0-9]$";

	private static final Pattern PATTERN = Pattern.compile(PHONE_PATTERN);

	@Override
	public boolean isValid(final String phone, final ConstraintValidatorContext context) {
		return (validatePhone(phone));
	}

	private boolean validatePhone(final String phone) {
		boolean flag = true;
		if (StringUtils.isNotBlank(phone)) {
			Matcher matcher = PATTERN.matcher(phone);
			flag = matcher.matches();
		}
		return flag;
	}
}