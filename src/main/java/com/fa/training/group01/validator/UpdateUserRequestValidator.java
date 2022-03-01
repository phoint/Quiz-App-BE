package com.fa.training.group01.validator;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.fa.training.group01.domain_model.Role;
import com.fa.training.group01.payload.UpdateUserRequest;
import com.fa.training.group01.service.IUserService;
import com.fa.training.group01.util.MessageBundle;
import com.fa.training.group01.util.Regex;

import lombok.Setter;

@Component
@Setter
public class UpdateUserRequestValidator implements Validator {
	private UpdateUserRequest target;
	private Errors errors;
	@Autowired
	private IUserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return UpdateUserRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		setTarget((UpdateUserRequest) target);
		setErrors(errors);
		if (hasEmptyFields()) {
			return;
		}
		validateUserID();
		validatePassword();
		validateRole();
		validateActive();

	}

	private boolean hasEmptyFields() {
		Field[] fields = this.target.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.getName().equals(UpdateUserRequest.Fields.password)) {
				continue;
			}
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, field.getName(), MessageBundle.FIELD_EMPTY_ERROR);
		}
		return errors.getErrorCount() > 0;
	}

	private void validateUserID() {
		if (!userService.existsById(target.getId())) {
			errors.rejectValue(UpdateUserRequest.Fields.id, MessageBundle.User.USER_NOT_FOUND);
		}
	}

	private void validatePassword() {
		if (StringUtils.hasLength(target.getPassword())
				&& !Regex.matchesPattern(Regex.PASSWORD_PATTERN, this.target.getPassword())) {
			errors.rejectValue(UpdateUserRequest.Fields.password, MessageBundle.User.PASSWORD_FORMAT);
		}

	}

	private void validateRole() {
		if (!Role.isRoleExists(target.getRole())) {
			errors.rejectValue(UpdateUserRequest.Fields.role, MessageBundle.Role.NOT_FOUND_ROLE);
		}
	}

	private void validateActive() {
		if (!(target.getActive().equalsIgnoreCase("true") || target.getActive().equalsIgnoreCase("false"))) {
			errors.rejectValue(UpdateUserRequest.Fields.active, MessageBundle.User.ACTIVE_FORMAT);
		}
	}

}
