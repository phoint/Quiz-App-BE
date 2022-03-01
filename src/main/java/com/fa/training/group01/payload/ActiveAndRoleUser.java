package com.fa.training.group01.payload;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ActiveAndRoleUser {
	private boolean active;
	private String role;
}
