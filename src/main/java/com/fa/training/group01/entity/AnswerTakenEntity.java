package com.fa.training.group01.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tbl_answer_taken")
public class AnswerTakenEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "question_id", referencedColumnName = "id")
	private QuestionEntity question;
	
	@ManyToOne
	@JoinColumn(name = "answer_id", referencedColumnName = "id")
	private AnswerEntity answer;
}
