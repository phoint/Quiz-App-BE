package com.fa.training.group01.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.rest.core.annotation.RestResource;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "tbl_topic")
public class TopicEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;

	@OneToMany(mappedBy = "topic")
	private List<QuizEntity> quizzes;
}
