package com.fa.training.group01.service;

import java.util.List;

import org.modelmapper.internal.bytebuddy.description.type.TypeDefinition.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.fa.training.group01.domain_model.FacebookUser;
import com.fa.training.group01.entity.UserEntity;
import com.fa.training.group01.payload.UpdateUserProfileRequest;

public interface IUserService extends CRUDService<UserEntity, Integer> {
	boolean existEmail(String email);

	UserEntity findOneByEmail(String email);

	UserEntity findByFacebookUser(FacebookUser facebookUser);

	UserEntity findOneByEmailAndActive(String email, Boolean active);

	UserEntity findOneByKeyToken(String keyToken);

	boolean existsByEmailAndActive(String email, Boolean active);

	boolean existsByKeyToken(String keyToken);

	boolean existsById(Integer id);

	Page<UserEntity> findAll(Pageable pageable);

	Page<UserEntity> findAll(Specification<UserEntity> specification, Pageable pageable);
}
