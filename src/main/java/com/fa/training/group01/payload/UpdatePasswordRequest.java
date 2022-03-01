package com.fa.training.group01.payload;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class UpdatePasswordRequest {
	private String username;
	private String currentPassword;
	private String newPassword;
	private String confirmNewPassword;
}
