package com.fa.training.group01.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.fa.training.group01.entity.TopicEntity;

@RepositoryRestResource(collectionResourceRel = "topics", path = "topics")
@Primary
public interface ITopicDataRestRepository extends JpaRepository<TopicEntity, Integer> {
}
