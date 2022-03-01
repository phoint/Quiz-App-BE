package com.fa.training.group01.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fa.training.group01.domain_model.Role;
import com.fa.training.group01.entity.RoleEntity;
import com.fa.training.group01.repository.IRoleRepository;
import com.fa.training.group01.service.IRoleService;

@Service
public class RoleService implements IRoleService {
	@Autowired
	IRoleRepository roleRepository;

	@Override
	public RoleEntity findByName(Role role) {
		return roleRepository.findOneByName(role.name);
	}

	@Override
	public boolean insert(RoleEntity entity) {
		return false;
	}

	@Override
	public boolean update(RoleEntity entity) {
		return false;
	}

	@Override
	public boolean delete(RoleEntity entity) {
		return false;
	}

	@Override
	public RoleEntity findOneByID(Integer id) {
		return null;
	}


}
