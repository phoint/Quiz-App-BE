//package com.fa.training.group01.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
//
//@Configuration
//@EnableAuthorizationServer
//public class AuthorizatonServerConfig extends AuthorizationServerConfigurerAdapter {
//	@Autowired
//	private AuthenticationManager authenticationManager;
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	@Autowired
//	private UserDetailsService userDetailsService;
//
//	@Bean
//	public TokenStore tokenStore() {
//		return new InMemoryTokenStore();
//	}
//
//	@Override
//	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//		endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager)
//		.userDetailsService(userDetailsService);
//	}
//
//	//	@Override
//	//	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//	//		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuth");
//	//	}
//	@Bean
//	@Primary
//	public DefaultTokenServices tokenServices() {
//		DefaultTokenServices tokenServices = new DefaultTokenServices();
//		tokenServices.setTokenStore(tokenStore());
//		return tokenServices;
//	}
//
//	@Override
//	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		clients.inMemory().withClient("client").secret(passwordEncoder.encode("9999")).scopes("read", "write")
//		.authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
//		.refreshTokenValiditySeconds(20000);
//	}
//}
