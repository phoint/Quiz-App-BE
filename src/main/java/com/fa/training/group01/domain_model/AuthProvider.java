package com.fa.training.group01.domain_model;

public enum AuthProvider {
	LOCAL, FACEBOOK;

	public static AuthProvider valueOfIgnoreCase(String value) {
		AuthProvider result = null;
		for (AuthProvider authProvider : AuthProvider.values()) {
			if (authProvider.name().equalsIgnoreCase(value)) {
				result = authProvider;
			}
		}
		return result;
	}
}
