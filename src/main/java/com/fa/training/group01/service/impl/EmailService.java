package com.fa.training.group01.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.fa.training.group01.service.IEmailService;


@Service
public class EmailService implements IEmailService {
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private SpringTemplateEngine templateEngine;
	@Value("${spring.mail.username}")
	private String fromEmail;

	@Async
	public void sendResetPasswordMail(Map<String, Object> model, String to) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			Context context = new Context();
			context.setVariables(model);
			message.setFrom(new InternetAddress(fromEmail, "Quiz App"));
			String html = templateEngine.process("reset-password", context);
			helper.setTo(to);
			helper.setText(html, true);
			helper.setSubject("Quên mật khẩu");
			helper.setFrom(fromEmail);
			mailSender.send(message);
		} catch (Exception e) {
			System.err.println(e);
		}
	}

}
