package com.fa.training.group01.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fa.training.group01.util.MessageBundle;

@ControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getCode();
			errors.put(fieldName, errorMessage);
		});
		return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Object> handleBadCredentialException(BadCredentialsException ex, WebRequest webRequest) {
		Map<String, String> body = new HashMap<>();
		body.put("message", MessageBundle.Authentication.USER_NOT_FOUND);
		return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(DisabledException.class)
	public ResponseEntity<Object> handleDisabledException(DisabledException ex, WebRequest webRequest) {
		Map<String, String> body = new HashMap<>();
		body.put("message", MessageBundle.Authentication.NOT_ACTIVE_USER);
		return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(PreAuthenticatedCredentialsNotFoundException.class)
	public ResponseEntity<Object> handlePreAuthenticatedCredentialsNotFoundException(
			PreAuthenticatedCredentialsNotFoundException ex, WebRequest webRequest) {
		Map<String, String> body = new HashMap<>();
		body.put("message", MessageBundle.Authentication.NOT_AUTHENTICATIED);
		return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
	}
}
