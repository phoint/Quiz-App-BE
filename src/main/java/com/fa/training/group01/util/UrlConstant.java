package com.fa.training.group01.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UrlConstant {
	public static final String CLIENT_HOSTNAME = "localhost";
	public static final String CLIENT_PORT = "8080";
	public static final String CLIENT_CONTEXT_PATH = "Quiz-FE";
	public static final String CLIENT_PREFIX_SERVER = "http://" + UrlConstant.CLIENT_HOSTNAME + ":"
			+ UrlConstant.CLIENT_PORT + "/" + UrlConstant.CLIENT_CONTEXT_PATH;
	public class Client {
		public static final String RESETPASSWORD = "reset-password";
	}
}
