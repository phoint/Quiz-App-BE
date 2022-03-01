package com.fa.training.group01.entity;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "topicCustom", types = TopicEntity.class)
public interface TopicProjection {
	String getName();
}
