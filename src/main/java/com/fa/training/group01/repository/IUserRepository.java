package com.fa.training.group01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.fa.training.group01.entity.UserEntity;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserEntity> {

	boolean existsByEmail(String email);

	UserEntity findOneByEmail(String email);

	UserEntity findOneByEmailAndActive(String email, Boolean active);

	UserEntity findOneByKeyToken(String keyToken);

	boolean existsByEmailAndActive(String email, Boolean active);

	boolean existsByKeyToken(String keyToken);

	boolean existsById(Integer id);

}
