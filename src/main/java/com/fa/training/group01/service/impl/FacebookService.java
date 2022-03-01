package com.fa.training.group01.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fa.training.group01.domain_model.FacebookUser;
import com.fa.training.group01.service.IFacebookService;

@Service
public class FacebookService implements IFacebookService {
	@Autowired
	private RestTemplate restTemplate;

	private final String FACEBOOK_GRAPH_API_BASE = "https://graph.facebook.com";

	@Override
	public FacebookUser getUser(String accessToken) {
		String path = "/me?fields={fields}&redirect={redirect}&access_token={access_token}";
		String fields = "email,name,id";
		final Map<String, String> variables = new HashMap<>();
		variables.put("fields", fields);
		variables.put("redirect", "false");
		variables.put("access_token", accessToken);
		return restTemplate.getForObject(FACEBOOK_GRAPH_API_BASE + path, FacebookUser.class, variables);
	}
}
