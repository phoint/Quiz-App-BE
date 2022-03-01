package com.fa.training.group01.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseMessage {

	public static Map<String, String> createErrorResponse(Errors errors) {
		Map<String, String> errorResponseModel = new HashMap<>();
		for (ObjectError objectError : errors.getAllErrors()) {
			String fieldErrors = ((FieldError) objectError).getField();
			errorResponseModel.put(fieldErrors, objectError.getCode());
		}
		return errorResponseModel;
	}

	public enum ResetPassword {
		SUCCESS, FAILED
	}

	@UtilityClass
	public static class ForgotPassword {
		public static final String NOT_FOUND_ACCOUNT = "email is not found";
		public static final String SUCCESS = "success";
	}

	@UtilityClass
	public static class UpdatePassword {
		public static final String SUCCESS = "success";
		public static final String FAILED = "failed";
	}

	@UtilityClass
	public static class Register {
		public static final String SUCCESS = "success";
		public static final String FAILED = "failed";
	}
	
	@UtilityClass
	public static class UpdateUser {
		public static final String SUCCESS = "success";
		public static final String FAILED = "failed";
	}
	@UtilityClass
	public static class UpdateProfile{
		public static final String SUCCESS = "success";
		public static final String FAILED = "failed";
	}

}
