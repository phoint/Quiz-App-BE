package com.fa.training.group01.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "tbl_quiz")
public class QuizEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;
	
	
	@OneToMany
	@JoinColumn(name ="quiz_id", referencedColumnName = "id")
	private List<PartEntity> parts;
	
	@OneToMany
	@JoinColumn(name = "quiz_id", referencedColumnName = "id")
	private List<QuestionEntity> questions;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="topic_id")
	private TopicEntity topic;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "likedQuizzes")
	private List<UserEntity> users;
}
