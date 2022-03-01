package com.fa.training.group01.repository;

import com.fa.training.group01.entity.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITopicRepository extends JpaRepository<TopicEntity, Integer> {
}
