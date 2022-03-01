package com.fa.training.group01.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.fa.training.group01.payload.ResetPasswordRequest;
import com.fa.training.group01.service.impl.UserService;
import com.fa.training.group01.util.MessageBundle;
import com.fa.training.group01.util.Regex;

@Component
public class ResetPasswordRequestValidator implements Validator {
	private ResetPasswordRequest target;
	private Errors errors;
	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return ResetPasswordRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ResetPasswordRequest resetPasswordRequest = (ResetPasswordRequest) target;
		this.target = resetPasswordRequest;
		this.errors = errors;
		if (hasEmptyFields()) {
			return;
		}
		validatePassword();
		validateConfirmPassword();
		validateToken();
	}

	private boolean hasEmptyFields() {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ResetPasswordRequest.Fields.token,
				MessageBundle.FIELD_EMPTY_ERROR);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ResetPasswordRequest.Fields.password,
				MessageBundle.FIELD_EMPTY_ERROR);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ResetPasswordRequest.Fields.confirmPassword,
				MessageBundle.FIELD_EMPTY_ERROR);
		return errors.getFieldErrorCount() > 0;
	}

	private void validatePassword() {
		if (!Regex.matchesPattern(Regex.PASSWORD_PATTERN, this.target.getPassword())) {
			errors.rejectValue(ResetPasswordRequest.Fields.password, MessageBundle.User.PASSWORD_FORMAT);
		}
	}

	private void validateConfirmPassword() {
		if (!target.getPassword().equals(target.getConfirmPassword())) {
			errors.rejectValue(ResetPasswordRequest.Fields.confirmPassword, MessageBundle.NOT_MATCH_PASSWORD_ERROR);
		}
	}

	private void validateToken() {
		if (!userService.existsByKeyToken(target.getToken())) {
			errors.rejectValue(ResetPasswordRequest.Fields.token, MessageBundle.User.NOT_FOUND_KEY_TOKEN);
		}
	}

}
