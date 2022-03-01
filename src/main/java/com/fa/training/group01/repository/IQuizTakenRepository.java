package com.fa.training.group01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.fa.training.group01.entity.AnswerEntity;
import com.fa.training.group01.entity.QuizTakenEntity;

@RepositoryRestResource(collectionResourceRel = "quiz-takens", path = "quiz-takens")
public interface IQuizTakenRepository extends JpaRepository<QuizTakenEntity, Integer> {
	
}
