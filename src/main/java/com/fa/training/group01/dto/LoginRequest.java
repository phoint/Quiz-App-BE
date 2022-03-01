package com.fa.training.group01.dto;

import lombok.Data;

@Data
public class LoginRequest {
	private String username;
	private String password;
	private String accessToken;
	private String authProvider;
}
