package com.fa.training.group01.controller.admin;

import com.fa.training.group01.dto.PageDTO;
import com.fa.training.group01.entity.TopicEntity;
import com.fa.training.group01.service.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TopicController {
    @Autowired
    ITopicService iTopicService;

    @GetMapping("/sTopic/page")
    public ResponseEntity<PageDTO> showTopic() {
        return showTopicPage(1);
    }

    @GetMapping("/sTopic/page/{currentPage}")
    public ResponseEntity<PageDTO> showTopicPage(@PathVariable int currentPage) {
        Page<TopicEntity> page = iTopicService.pageTopic(currentPage,0);
        PageDTO pageDTO = new PageDTO(page);
        return ResponseEntity.status(HttpStatus.OK).body(pageDTO);
    }

    @GetMapping({"/sTopic/all"})
    public ResponseEntity<List<TopicEntity>> showAllTopic(){
        return ResponseEntity.ok(iTopicService.listAllTopic());
    }

    @GetMapping({"/sTopic/{id}"})
    public ResponseEntity<TopicEntity> getTopic(@PathVariable int id){
        return ResponseEntity.ok(iTopicService.findOneByID(id));
    }

    @PostMapping({"/sTopic"})
    public ResponseEntity<Boolean> insertTopic(@RequestBody TopicEntity topicEntity){
        return ResponseEntity.ok(iTopicService.insert(topicEntity));
    }

    @PutMapping({"/sTopic"})
    public ResponseEntity<Boolean> updateTopic(@RequestBody TopicEntity topicEntity){
        return ResponseEntity.ok(iTopicService.update(topicEntity));
    }

    @DeleteMapping({"/sTopic"})
    public ResponseEntity<Boolean> deleteTopic(@RequestBody TopicEntity topicEntity){
        return ResponseEntity.ok(iTopicService.delete(topicEntity));
    }

    @DeleteMapping({"/sTopic/delete/"})
    public ResponseEntity<Boolean> deleteTopicById(@RequestParam int id){
        return ResponseEntity.ok(iTopicService.deleteById(id));
    }
}
