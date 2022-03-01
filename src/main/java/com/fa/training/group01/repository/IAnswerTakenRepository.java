package com.fa.training.group01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.fa.training.group01.entity.AnswerTakenEntity;

@RepositoryRestResource(collectionResourceRel = "ans-takens", path = "ans-takens")
public interface IAnswerTakenRepository extends JpaRepository<AnswerTakenEntity, Integer> {

}
