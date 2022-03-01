package com.fa.training.group01.service;

import com.fa.training.group01.domain_model.FacebookUser;

public interface IFacebookService {
	public FacebookUser getUser(String accessToken);
}
