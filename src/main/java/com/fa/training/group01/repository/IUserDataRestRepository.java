package com.fa.training.group01.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.fa.training.group01.entity.UserEntity;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
@Primary
public interface IUserDataRestRepository extends JpaRepository<UserEntity, Integer> {

//	boolean existsByEmail(String email);
//
//	UserEntity findOneByEmail(String email);
//
//	UserEntity findOneByEmailAndActive(String email, Boolean active);
//
//	UserEntity findOneByKeyToken(String keyToken);
//
//	boolean existsByEmailAndActive(String email, Boolean active);
//
//	boolean existsByKeyToken(String keyToken);

}
