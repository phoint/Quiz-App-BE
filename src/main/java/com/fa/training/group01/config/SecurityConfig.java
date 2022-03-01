package com.fa.training.group01.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fa.training.group01.domain_model.Role;
import com.fa.training.group01.filter.AuthTokenFilter;
import com.fa.training.group01.security.AuthAccessDeniedHandler;
import com.fa.training.group01.security.AuthEntryPoint;
import com.fa.training.group01.util.RequestURL;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	private static final String[] PUBLIC_MATCHERS = { "/login", "/register", "/forgot-password", "/reset-password",
//			"/exists-reset-password-token" };
	private static final String[] PUBLIC_MATCHERS = RequestURL.Public.ALL_PATH;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthTokenFilter authTokenFilter;
	@Autowired
	private AuthEntryPoint authEntryPoint;
	@Autowired
	private AuthAccessDeniedHandler authAccessDeniedHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().and().authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll()
				.antMatchers("/api/**").permitAll()
				.antMatchers(RequestURL.Student.User.USER_PATH + "/**")
				.hasAnyAuthority(Role.STUDENT.name, Role.ADMIN.name).antMatchers(RequestURL.Admin.ADMIN_PATH + "/**")
				.hasAnyAuthority(Role.ADMIN.name).anyRequest().authenticated().and().exceptionHandling()
				.authenticationEntryPoint(authEntryPoint).accessDeniedHandler(authAccessDeniedHandler).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

//		http.csrf().disable().cors().and().authorizeRequests().anyRequest().permitAll().and().sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

}
