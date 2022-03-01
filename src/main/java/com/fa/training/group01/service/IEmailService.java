package com.fa.training.group01.service;

import java.util.Map;

public interface IEmailService {
	public void sendResetPasswordMail(Map<String, Object> model, String to);
}
