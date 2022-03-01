package com.fa.training.group01.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.fa.training.group01.dto.RegisterFormUserDTO;
import com.fa.training.group01.service.IUserService;
import com.fa.training.group01.util.MessageBundle;
import com.fa.training.group01.util.Regex;

import lombok.ToString;

@Component
public class RegisterUserFormValidator implements Validator {
	@Autowired
	IUserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return RegisterFormUserDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		RegisterFormUserDTO registerFormUserDTO = (RegisterFormUserDTO) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, RegisterFormUserDTO.Fields.email,
				MessageBundle.FIELD_EMPTY_ERROR);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, RegisterFormUserDTO.Fields.password,
				MessageBundle.FIELD_EMPTY_ERROR);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, RegisterFormUserDTO.Fields.confirmPassword,
				MessageBundle.FIELD_EMPTY_ERROR);
		if (errors.hasErrors())
			return;
		// Validate email field
		if (!Regex.matchesPattern(Regex.EMAIL_PATTERN, registerFormUserDTO.getEmail())) {
			errors.rejectValue(RegisterFormUserDTO.Fields.email, MessageBundle.User.EMAIL_FORMAT);
		} else if (userService.existEmail(registerFormUserDTO.getEmail())) {
			errors.rejectValue(RegisterFormUserDTO.Fields.email, MessageBundle.User.EMAIL_EXIST);
		}
		// Validate password field
		if (!Regex.matchesPattern(Regex.PASSWORD_PATTERN, registerFormUserDTO.getPassword())) {
			errors.rejectValue(RegisterFormUserDTO.Fields.password, MessageBundle.User.PASSWORD_FORMAT);
		}
		// Validate confirmPassword field
		if (!registerFormUserDTO.getPassword().equals(registerFormUserDTO.getConfirmPassword())) {
			errors.rejectValue(RegisterFormUserDTO.Fields.confirmPassword, MessageBundle.NOT_MATCH_PASSWORD_ERROR);
		}

	}

}
