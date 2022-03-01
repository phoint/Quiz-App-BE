package com.fa.training.group01.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.rest.core.annotation.RestResource;

import lombok.Data;

@Data
@Entity
@Table(name = "tbl_quiz_taken")
public class QuizTakenEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int scored;
	
	@ManyToOne
	@JoinColumn(name = "quiz_id", referencedColumnName = "id")
	private QuizEntity quiz;
	
	@OneToMany
	@JoinColumn(name = "quiz_taken_id")
	@RestResource(rel = "ans-takens", path = "ans-takens")
	private List<AnswerTakenEntity> answerTakens;

//	@Column(name = "created_at")
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date createdAt;
}
