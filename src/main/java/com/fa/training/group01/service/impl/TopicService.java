package com.fa.training.group01.service.impl;

import com.fa.training.group01.entity.TopicEntity;
import com.fa.training.group01.repository.ITopicRepository;
import com.fa.training.group01.service.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class TopicService implements ITopicService {
    @Autowired
    ITopicRepository topicRepository;

    @Override
    public List<TopicEntity> listAllTopic() {
        return topicRepository.findAll();
    }

    @Override
    public boolean deleteById(int id) {
        try {
            topicRepository.deleteById(id);
            return true;
        }catch (Exception e){
            System.err.println(e);
            return false;
        }
    }

    @Override
    public Page<TopicEntity> pageTopic(int currentPage,int pageSide) {
        Pageable pageable = PageRequest.of(currentPage - 1,pageSide == 0?3:pageSide);
        return topicRepository.findAll(pageable);
    }

    @Override
    public boolean insert(TopicEntity entity) {
        try {
            topicRepository.save(entity);
            return true;
        }catch (Exception e){
            System.err.println(e);
            return false;
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public boolean update(TopicEntity entity) {
        try {
            topicRepository.save(entity);
            return true;
        }catch (Exception e){
            System.err.println(e);
            return false;
        }
    }

    @Override
    public boolean delete(TopicEntity entity) {
        try {
            topicRepository.delete(entity);
            return true;
        }catch (Exception e){
            System.err.println(e);
            return false;
        }
    }

    @Override
    public TopicEntity findOneByID(Integer id) {
        return topicRepository.findById(id).orElse(null);
    }
}
