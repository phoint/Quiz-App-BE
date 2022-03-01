package com.fa.training.group01.service;

import com.fa.training.group01.domain_model.Role;
import com.fa.training.group01.entity.RoleEntity;

public interface IRoleService extends CRUDService<RoleEntity, Integer> {
	RoleEntity findByName(Role role);
}
