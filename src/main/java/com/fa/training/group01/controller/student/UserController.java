package com.fa.training.group01.controller.student;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fa.training.group01.domain_model.CurrentUser;
import com.fa.training.group01.entity.UserEntity;
import com.fa.training.group01.payload.UpdatePasswordRequest;
import com.fa.training.group01.payload.UpdateUserProfileRequest;
import com.fa.training.group01.service.IUserService;
import com.fa.training.group01.service.impl.CloudinaryService;
import com.fa.training.group01.service.impl.UserDetailsImpl;
import com.fa.training.group01.util.FileHelper;
import com.fa.training.group01.util.MessageBundle;
import com.fa.training.group01.util.Regex;
import com.fa.training.group01.util.RequestURL;
import com.fa.training.group01.util.ResponseMessage;
import com.fa.training.group01.util.StringHelper;
import com.fa.training.group01.validator.UpdatePasswordValidator;
import com.fa.training.group01.validator.UpdateUserProfileRequestValidator;

@RestController
@RequestMapping(RequestURL.Student.User.USER_PATH)
public class UserController {
	/* Database Services */
	@Autowired
	private IUserService userService;
	/* Component Objects */
	@Autowired
	private UpdatePasswordValidator updatePasswordValidator;
	@Autowired
	private UpdateUserProfileRequestValidator updateUserProfileRequestValidator;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private CloudinaryService cloudinaryService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		if (StringHelper.lowerFirstLetter(UpdateUserProfileRequest.class.getSimpleName())
				.equals(binder.getObjectName())) {
			binder.setValidator(updateUserProfileRequestValidator);
		}
	}

	@RequestMapping(value = RequestURL.Student.User.Path.GET_ACCOUNT, method = RequestMethod.GET)
	public ResponseEntity<UserEntity> getAccount(@CurrentUser UserDetailsImpl userDetailsImpl) {
		return new ResponseEntity<UserEntity>(userService.findOneByEmail(userDetailsImpl.getUsername()), HttpStatus.OK);
	}

	@RequestMapping(value = RequestURL.Student.User.Path.UPDATE_PASSWORD, method = RequestMethod.POST)
	public ResponseEntity<?> updatePassword(@CurrentUser UserDetailsImpl userDetailsImpl,
			@RequestBody UpdatePasswordRequest updatePasswordRequest, Errors errors) {
		// Validate Request
		String username = userDetailsImpl.getUsername();
		updatePasswordRequest.setUsername(username);
		updatePasswordValidator.validate(updatePasswordRequest, errors);
		if (errors.hasErrors()) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
					.body(ResponseMessage.createErrorResponse(errors));
		}
		// Update User into database
		String result = ResponseMessage.UpdatePassword.SUCCESS;
		UserEntity userEntity = userService.findOneByEmail(username);
		userEntity.setPassword(passwordEncoder.encode(updatePasswordRequest.getNewPassword()));
		if (!userService.update(userEntity)) {
			result = ResponseMessage.UpdatePassword.FAILED;
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	@RequestMapping(value = RequestURL.Student.User.Path.UPDATE_PROFILE, method = RequestMethod.POST)
	public ResponseEntity<?> updateProfile(@CurrentUser UserDetailsImpl userDetailsImpl,
			@RequestParam(name = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "avatar", required = false) MultipartFile avatar) {
		UpdateUserProfileRequest updateUserProfileRequest = new UpdateUserProfileRequest();
		updateUserProfileRequest.setAvatar(avatar);
		updateUserProfileRequest.setName(name);

		Map<String, String> errorResponseMessage = errorResponseMessage(updateUserProfileRequest);
		if (errorResponseMessage.size() > 0) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponseMessage);
		}

		UserEntity userEntity = userService.findOneByEmail(userDetailsImpl.getUsername());
		if (StringUtils.hasText(updateUserProfileRequest.getName())) {
			userEntity.setName(updateUserProfileRequest.getName());
		}

		if (FileHelper.hasData(avatar)) {
			String url = cloudinaryService.uploadImage(avatar);
			String oldURL = userEntity.getAvatar();
			if (StringUtils.hasText(oldURL)) {
				cloudinaryService.deleteImage(cloudinaryService.extractImagePublicID(oldURL));
			}
			userEntity.setAvatar(url);
		}
		if (!userService.update(userEntity)) {
			return ResponseEntity.ok(ResponseMessage.UpdateProfile.FAILED);
		}
		return ResponseEntity.ok(ResponseMessage.UpdateProfile.SUCCESS);
	}

	private Map<String, String> errorResponseMessage(UpdateUserProfileRequest updateUserProfileRequest) {
		Map<String, String> errors = new HashMap<>();
		validateAvatarFile(updateUserProfileRequest, errors);
		validateName(updateUserProfileRequest, errors);
		return errors;
	}

	private void validateName(UpdateUserProfileRequest target, Map<String, String> errors) {
		if (StringUtils.hasText(target.getName()) && !Regex.matchesPattern(Regex.NAME_PATTERN, target.getName())) {
			errors.put(UpdateUserProfileRequest.Fields.name, MessageBundle.User.NAME_FORMAT);
		}
	}

	private void validateAvatarFile(UpdateUserProfileRequest target, Map<String, String> errors) {
		if (!FileHelper.hasData(target.getAvatar())) {
			return;
		}
		if (FileHelper.isFileOverSize(target.getAvatar(), CloudinaryService.MAX_IMAGE_FILE_SIZE)) {
			errors.put(UpdateUserProfileRequest.Fields.avatar, MessageBundle.User.AVATAR_OVER_SIZE);
		}
	}

}
