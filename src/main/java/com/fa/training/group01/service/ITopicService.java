package com.fa.training.group01.service;

import com.fa.training.group01.entity.TopicEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ITopicService extends CRUDService<TopicEntity,Integer> {
    List<TopicEntity> listAllTopic();

    boolean deleteById(int id);

    Page<TopicEntity> pageTopic(int currentPage,int pageSide);

}
