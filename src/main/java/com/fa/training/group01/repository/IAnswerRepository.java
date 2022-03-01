package com.fa.training.group01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.fa.training.group01.entity.AnswerEntity;

@RepositoryRestResource(collectionResourceRel = "answers", path = "answers")
public interface IAnswerRepository extends JpaRepository<AnswerEntity, Integer> {

}
