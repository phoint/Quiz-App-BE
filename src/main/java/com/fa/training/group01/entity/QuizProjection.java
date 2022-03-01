package com.fa.training.group01.entity;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "quizCustom", types = QuizEntity.class)
public interface QuizProjection {
	int getId();
	String getTitle();
	String getContent();
	
	TopicProjection getTopic();
}
