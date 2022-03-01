package com.fa.training.group01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fa.training.group01.entity.RoleEntity;

public interface IRoleRepository extends JpaRepository<RoleEntity, Integer> {
	RoleEntity findOneByName(String name);
	
}
