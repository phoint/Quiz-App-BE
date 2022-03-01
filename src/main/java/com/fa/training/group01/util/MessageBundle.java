package com.fa.training.group01.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageBundle {
	public static final String FIELD_EMPTY_ERROR = "Bạn không được để trống trường dữ liệu này";
	public static final String NOT_MATCH_PASSWORD_ERROR = "Mật khẩu và xác nhận mật khẩu không trùng khớp";

	/*
	 * User Field Error Message
	 */
	@UtilityClass
	public static class User {

		public static final String USER_NOT_FOUND = "Tài khoản trên không tồn tại";
		
		/* NAME */
		public static final String NAME_FORMAT="Họ và tên của bạn không hợp lệ";
		/* EMAIL */
		public static final String EMAIL_FORMAT = "Email của bạn không hợp lệ";
		public static final String EMAIL_EXIST = "Email của bạn đã được sử dụng";
		/* PASSWORD */
		public static final String PASSWORD_FORMAT = "Mật khẩu của bạn phải có ít nhất 1 ký tự thường, hoa, số, đặc biệt và ít nhất 8 ký tự";
		public static final String NOT_FOUND_KEY_TOKEN = "Mã để reset mật khẩu của bạn không tồn tại";
		public static final String NOT_MATCH_CURRENT_PASSWORD = "Bạn đã nhập sai mật khẩu hiện tại";
		/* ACTIVE */
		public static final String ACTIVE_FORMAT = "Trạng thái của user chỉ có thế là active và không active";
		public static final String AVATAR_OVER_SIZE="Avatar bạn upload lên không được quá 10MB";
	}

	@UtilityClass
	public static class Role {
		public static final String NOT_FOUND_ROLE = "Role bạn chọn không tồn tại";
	}

	@UtilityClass
	public static class Authentication {
		public static final String USER_NOT_FOUND = "Tài khoản hoặc mật khẩu của bạn không trùng đúng";
		public static final String NOT_ACTIVE_USER = "Tài khoản của bạn đã bị khoá";
		public static final String NOT_AUTHENTICATIED = "Bạn không có quyền hạn truy cập vào tài nguyên này";
		public static final String NOT_AUTHORIZATIED = "Bạn không đủ quyền hạn truy cập vào tài nguyên này";
	}
}
