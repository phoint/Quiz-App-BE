package com.fa.training.group01.payload;


import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class UpdateUserRequest {
	private Integer id;
	private String password;
	private String active;
	private String role;
}
