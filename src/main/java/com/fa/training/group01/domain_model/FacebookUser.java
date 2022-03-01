package com.fa.training.group01.domain_model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class FacebookUser {
	@JsonProperty("id")
	private String facebookID;
	private String email;
	private String name;
}
