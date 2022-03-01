package com.fa.training.group01.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import com.fa.training.group01.util.Regex;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class RegisterFormUserDTO {
	@Email
	private String email;
	@Pattern(regexp = Regex.PASSWORD_PATTERN)
	private String password;
	private String confirmPassword;
}
