package com.fa.training.group01.util;

import java.util.regex.Pattern;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Regex {
	/*
	 * At least 8 chars Contains at least one digit Contains at least one lower
	 * alpha char and one upper alpha char Contains at least one char within a set
	 * of special chars (@#%$^ etc.) Does not contain space, tab, etc.
	 */
	public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
	/*
	 * RFC 5322 standard
	 */
	public static final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

	public static final String NAME_PATTERN = "^[\\p{L} .'-]+$";

	public static boolean matchesPattern(String pattern, String target) {
		return Pattern.compile(pattern).matcher(target).matches();
	}
}
