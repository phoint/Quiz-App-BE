package com.fa.training.group01.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fa.training.group01.domain_model.AuthProvider;
import com.fa.training.group01.domain_model.FacebookUser;
import com.fa.training.group01.domain_model.Role;
import com.fa.training.group01.dto.LoginRequest;
import com.fa.training.group01.entity.UserEntity;
import com.fa.training.group01.payload.ActiveAndRoleUser;
import com.fa.training.group01.payload.AuthenciationToken;
import com.fa.training.group01.security.jwt.JWTUtil;
import com.fa.training.group01.service.impl.FacebookService;
import com.fa.training.group01.service.impl.UserService;
import com.fa.training.group01.util.MessageBundle;
import com.fa.training.group01.util.RequestURL;

@RestController
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private FacebookService facebookService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = RequestURL.Public.Path.LOGIN, method = RequestMethod.POST)
	public AuthenciationToken login(@RequestBody LoginRequest loginRequest) {
		if (AuthProvider.FACEBOOK == AuthProvider.valueOfIgnoreCase(loginRequest.getAuthProvider())) {
			FacebookUser facebookUser = facebookService.getUser(loginRequest.getAccessToken());
			UserEntity userEntity = userService.findByFacebookUser(facebookUser);
			loginRequest.setUsername(userEntity.getEmail());
			if (!userEntity.getActive()) {
				throw new UsernameNotFoundException(MessageBundle.Authentication.USER_NOT_FOUND);
			}
		} else {
			authenticate(loginRequest);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		return new AuthenciationToken(jwt);
	}

	@RequestMapping(value = RequestURL.Public.Path.ROLE_ACTIVE, method = RequestMethod.GET)
	public ActiveAndRoleUser getRoleAndActive(@RequestParam("email") String email) {
		if (!StringUtils.hasText(email))
			return null;
		UserEntity userEntity = userService.findOneByEmail(email);
		if (userEntity != null) {
			return ActiveAndRoleUser.builder().role(Role.valueOf(userEntity.getRole()).name)
					.active(userEntity.getActive()).build();
		}
		return null;
	}

	private void authenticate(LoginRequest loginForm) throws AuthenticationException {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		}
	}

}
