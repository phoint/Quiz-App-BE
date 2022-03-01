package com.fa.training.group01.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class RequestURL {
	public enum Public {
		LOGIN(Path.LOGIN), REGISTER(Path.REGISTER), FORGOT_PASSWORD(Path.FORGOT_PASSWORD),
		RESET_PASSWORD(Path.RESET_PASSWORD), EXISTS_RESET_PASSWORD_TOKEN(Path.EXISTS_RESET_PASSWORD_TOKEN),
		ROLE_ACTIVE(Path.ROLE_ACTIVE), QUIZ_LIST(Path.QUIZ_LIST), TOPIC_LIST(Path.TOPIC_LIST);

		public final String path;
		public static final String[] ALL_PATH = paths();

		Public(String path) {
			this.path = path;
		}

		private static final String[] paths() {
			Public[] publics = Public.values();
			String[] values = new String[publics.length];
			for (int i = 0; i < values.length; i++) {
				values[i] = publics[i].path;
			}
			return values;
		}

		public static final class Path {
			public static final String LOGIN = "/login";
			public static final String REGISTER = "/register";
			public static final String FORGOT_PASSWORD = "/forgot-password";
			public static final String RESET_PASSWORD = "/reset-password";
			public static final String EXISTS_RESET_PASSWORD_TOKEN = "/exists-reset-password-token";
			public static final String ROLE_ACTIVE = "/active-role";
			public static final String QUIZ_LIST = "/api/quizzes";
			public static final String TOPIC_LIST = "/api/topics";
		}
	}

	@UtilityClass
	public static final class Student {

		public enum User {
			GET_ACCOUNT(Path.GET_ACCOUNT), UPDATE_PASSWORD(Path.UPDATE_PASSWORD), UPDATE_PROFILE(Path.UPDATE_PROFILE);

			public static final String USER_PATH = "/user";

			public final String path;

			User(String path) {
				this.path = path;
			}

			public static final class Path {
				public static final String GET_ACCOUNT = "/account";
				public static final String UPDATE_PASSWORD = "/update-password";
				public static final String UPDATE_PROFILE = "/update-profile";
			}
		}
	}

	@UtilityClass
	public static final class Admin {
		public static final String ADMIN_PATH = "/admin";

		public enum User {
			ACCOUNT_LIST(Path.ACCOUNT_LIST);

			public static final String USER_PATH = "/user";
			public final String path;

			User(String path) {
				this.path = path;
			}

			public static final class Path {
				public static final String ACCOUNT_LIST = "/list";
				public static final String UPDATE_USER = "/update";
				public static final String GET_USER = "/get/{id}";
			}
		}

	}

}
