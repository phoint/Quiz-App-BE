package com.fa.training.group01.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenciationToken {
	@JsonProperty("access_token")
	private String accessToken;
}
