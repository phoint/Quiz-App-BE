package com.fa.training.group01.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fa.training.group01.util.MessageBundle;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
//		response.setContentType(MediaType.TEXT_HTML_VALUE);
//		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
//		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().println(MessageBundle.Authentication.NOT_AUTHENTICATIED);
	}

}
