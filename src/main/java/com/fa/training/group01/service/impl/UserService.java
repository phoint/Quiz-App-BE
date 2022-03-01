package com.fa.training.group01.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.fa.training.group01.domain_model.FacebookUser;
import com.fa.training.group01.domain_model.Role;
import com.fa.training.group01.entity.UserEntity;
import com.fa.training.group01.repository.IUserRepository;
import com.fa.training.group01.service.IRoleService;
import com.fa.training.group01.service.IUserService;
import com.fa.training.group01.util.StringHelper;

@Repository
public class UserService implements IUserService {
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private IRoleService roleService;

	@Override
	public boolean insert(UserEntity userEntity) {
		try {
//			userEntity.setCreatedAt(new Date());
			userRepository.save(userEntity);
			return true;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
	}

	@Override
	public boolean existEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public boolean update(UserEntity userEntity) {
		try {
//			userEntity.setUpdatedAt(new Date());
			userRepository.save(userEntity);
			return true;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
	}

	@Override
	public boolean delete(UserEntity userEntity) {
		try {
			userRepository.delete(userEntity);
			return true;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
	}

	@Override
	public UserEntity findOneByID(Integer id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public UserEntity findOneByEmail(String email) {
		return userRepository.findOneByEmail(email);
	}

	@Override
	public UserEntity findByFacebookUser(FacebookUser facebookUser) {
		UserEntity userEntity = userRepository.findOneByEmail(facebookUser.getEmail());
		if (userEntity == null) {
			userEntity = new UserEntity();
			userEntity.setEmail(facebookUser.getEmail());
			userEntity.setName(facebookUser.getName());
			userEntity.setRole(roleService.findByName(Role.STUDENT));
			userEntity.setPassword(passwordEncoder.encode(StringHelper.generatePassword(20)));
			userEntity.setActive(true);
			userEntity = userRepository.save(userEntity);
		}
		return userEntity;
	}

	@Override
	public boolean existsByEmailAndActive(String email, Boolean active) {
		return userRepository.existsByEmailAndActive(email, active);
	}

	@Override
	public boolean existsByKeyToken(String key) {
		return userRepository.existsByKeyToken(key);
	}

	@Override
	public UserEntity findOneByEmailAndActive(String email, Boolean active) {
		return userRepository.findOneByEmailAndActive(email, active);
	}

	@Override
	public UserEntity findOneByKeyToken(String keyToken) {
		return userRepository.findOneByKeyToken(keyToken);
	}

	@Override
	public Page<UserEntity> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public Page<UserEntity> findAll(Specification<UserEntity> specification, Pageable pageable) {
		return userRepository.findAll(specification, pageable);
	}

	@Override
	public boolean existsById(Integer id) {
		return userRepository.existsById(id);
	}


}
