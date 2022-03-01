package com.fa.training.group01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.fa.training.group01.entity.QuestionEntity;

@RepositoryRestResource(collectionResourceRel = "questions", path = "questions")
public interface IQuestionRepository extends JpaRepository<QuestionEntity, Integer> {
}
