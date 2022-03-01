package com.fa.training.group01.validator;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.fa.training.group01.payload.UpdatePasswordRequest;
import com.fa.training.group01.service.IUserService;
import com.fa.training.group01.util.MessageBundle;
import com.fa.training.group01.util.Regex;

import lombok.Setter;

@Component
@Setter
public class UpdatePasswordValidator implements Validator {
	private UpdatePasswordRequest target;
	private Errors errors;
	@Autowired
	private IUserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public boolean supports(Class<?> clazz) {
		return UpdatePasswordRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		setTarget((UpdatePasswordRequest) target);
		setErrors(errors);
		if (hasEmptyFields()) {
			return;
		}
		validateCurrentPassword();
		validateNewPassword();
		validateConfirmNewPassword();

	}

	private boolean hasEmptyFields() {
		Field[] fields = this.target.getClass().getDeclaredFields();
		for (Field field : fields) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, field.getName(), MessageBundle.FIELD_EMPTY_ERROR);
		}
		return errors.getErrorCount() > 0;
	}

	private void validateCurrentPassword() {
		if (!passwordEncoder.matches(target.getCurrentPassword(),
				userService.findOneByEmail(target.getUsername()).getPassword())) {
			errors.rejectValue(UpdatePasswordRequest.Fields.currentPassword,
					MessageBundle.User.NOT_MATCH_CURRENT_PASSWORD);
		}
	}

	private void validateNewPassword() {
		if (!Regex.matchesPattern(Regex.PASSWORD_PATTERN, this.target.getNewPassword())) {
			errors.rejectValue(UpdatePasswordRequest.Fields.newPassword, MessageBundle.User.PASSWORD_FORMAT);
		}
	}

	private void validateConfirmNewPassword() {
		if (!target.getNewPassword().equals(target.getConfirmNewPassword())) {
			errors.rejectValue(UpdatePasswordRequest.Fields.confirmNewPassword, MessageBundle.NOT_MATCH_PASSWORD_ERROR);
		}
	}

}
