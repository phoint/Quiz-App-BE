package com.fa.training.group01.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.rest.core.annotation.RestResource;

import lombok.Data;

@Data
@Entity
@Table(name = "tbl_user")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "name", nullable = true)
	private String name;
	@Column(name = "avatar", nullable = false)
	private String avatar;
	@Column(name = "key_token", nullable = true, unique = true)
	private String keyToken;
	@Column(name = "active", nullable = false)
	private Boolean active;
	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private RoleEntity role;

	@OneToMany
	@JoinColumn(name = "user_id")
	@RestResource(rel = "quiz-takens", path = "take")
	private List<QuizTakenEntity> quizTakens;

	@OneToMany
	@JoinColumn(name = "admin_id", referencedColumnName = "id")
	@RestResource(rel = "quiz-created", path = "create")
	private List<QuizEntity> quizzes;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "tbl_wishlist", 
	joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "quiz_id", referencedColumnName = "id"))
	private List<QuizEntity> likedQuizzes;
//	@Column(name = "created_at")
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date createdAt;
//	@Column(name = "updated_at")
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date updatedAt;

}
