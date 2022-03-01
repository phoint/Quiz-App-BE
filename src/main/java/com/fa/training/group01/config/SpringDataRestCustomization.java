package com.fa.training.group01.config;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy.RepositoryDetectionStrategies;
import org.springframework.data.rest.core.mapping.ResourceMappings;
import org.springframework.data.rest.webmvc.RepositoryRestHandlerMapping;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.fa.training.group01.entity.AnswerEntity;
import com.fa.training.group01.entity.AnswerTakenEntity;
import com.fa.training.group01.entity.PartEntity;
import com.fa.training.group01.entity.QuestionEntity;
import com.fa.training.group01.entity.QuizEntity;
import com.fa.training.group01.entity.QuizTakenEntity;
import com.fa.training.group01.entity.SectionEntity;
import com.fa.training.group01.entity.TopicEntity;
import com.fa.training.group01.entity.UserEntity;

@Component
public class SpringDataRestCustomization implements RepositoryRestConfigurer {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		config.setRepositoryDetectionStrategy(RepositoryDetectionStrategies.ANNOTATED).exposeIdsFor(QuizEntity.class,
				PartEntity.class, SectionEntity.class, QuestionEntity.class, AnswerEntity.class, QuizTakenEntity.class,
				AnswerTakenEntity.class, UserEntity.class, TopicEntity.class).setBasePath("/api");
	}	
}
