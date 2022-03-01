package com.fa.training.group01.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class ResetPasswordRequest {
	@JsonProperty("token")
	private String token;
	private String password;
	private String confirmPassword;
}
