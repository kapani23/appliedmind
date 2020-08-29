package com.appliedmind.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN);

	@Override
	public boolean isValid(final String email, final ConstraintValidatorContext context) {
		return (validateEmail(email));
	}

	private boolean validateEmail(final String email) {
		boolean flag = true;
		if (StringUtils.isNotBlank(email)) {
			Matcher matcher = PATTERN.matcher(email);
			flag = matcher.matches();
		}
		return flag;
	}
}