package com.fa.training.group01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.fa.training.group01.entity.PartEntity;

@RepositoryRestResource(collectionResourceRel = "parts", path = "parts")
public interface IPartEntity extends JpaRepository<PartEntity, Integer> {

}
