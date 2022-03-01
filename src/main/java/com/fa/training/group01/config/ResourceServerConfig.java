//package com.fa.training.group01.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//	private static final String[] PUBLIC_MATCHERS = { "/user/register", "/user/login" };
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable().cors().and().authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest()
//				.authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
//	}
//}
