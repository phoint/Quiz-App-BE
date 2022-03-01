package com.fa.training.group01.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fa.training.group01.domain_model.Role;
import com.fa.training.group01.entity.UserEntity;
public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 6223794069670288379L;
	private UserEntity userEntity;

	public UserDetailsImpl(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		// add user's authorities
		setAuths.add(new SimpleGrantedAuthority(Role.valueOf(userEntity.getRole()).name));
		System.out.println(Role.valueOf(userEntity.getRole()).name);
		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);
		return result;
	}

	@Override
	public String getPassword() {
		return userEntity.getPassword();
	}

	@Override
	public String getUsername() {
		return userEntity.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return userEntity.getActive();
	}

}
