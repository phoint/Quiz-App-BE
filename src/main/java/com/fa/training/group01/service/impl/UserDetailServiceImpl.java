package com.fa.training.group01.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fa.training.group01.entity.UserEntity;
import com.fa.training.group01.service.IUserService;
import com.fa.training.group01.util.MessageBundle;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	@Autowired
	IUserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity userEntity = userService.findOneByEmail(username);
		if (userEntity == null) {
			throw new UsernameNotFoundException(MessageBundle.Authentication.USER_NOT_FOUND);
		}
		return new UserDetailsImpl(userEntity);
	}

}
