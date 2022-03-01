package com.fa.training.group01.validator;

import java.lang.reflect.Field;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.fa.training.group01.payload.UpdateUserProfileRequest;
import com.fa.training.group01.payload.UpdateUserRequest;
import com.fa.training.group01.service.impl.CloudinaryService;
import com.fa.training.group01.util.FileHelper;
import com.fa.training.group01.util.MessageBundle;
import com.fa.training.group01.util.Regex;

import lombok.Data;
import lombok.Setter;

@Component
@Setter
public class UpdateUserProfileRequestValidator implements Validator {
	private UpdateUserProfileRequest target;
	private Errors errors;

	@Override
	public boolean supports(Class<?> clazz) {
		return UpdateUserProfileRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		setTarget((UpdateUserProfileRequest) target);
		setErrors(errors);
		validateName();
		validateAvatarFile();
	}

	private void validateName() {
		if (StringUtils.hasText(target.getName()) && !Regex.matchesPattern(Regex.NAME_PATTERN, target.getName())) {
			errors.rejectValue(UpdateUserProfileRequest.Fields.name, MessageBundle.User.NAME_FORMAT);
		}
	}

	private void validateAvatarFile() {
		if(!FileHelper.hasData(target.getAvatar())) {
			return ;
		}
		if (FileHelper.isFileOverSize(target.getAvatar(), CloudinaryService.MAX_IMAGE_FILE_SIZE)) {
			errors.rejectValue(UpdateUserProfileRequest.Fields.avatar, MessageBundle.User.AVATAR_OVER_SIZE);
		}
	}
}
