package com.fa.training.group01.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.fa.training.group01.entity.QuizEntity;
import com.fa.training.group01.entity.QuizProjection;

@RepositoryRestResource(collectionResourceRel = "quizzes", path = "quizzes", excerptProjection = QuizProjection.class)
public interface IQuizReporitory extends CrudRepository<QuizEntity, Integer> {

}
