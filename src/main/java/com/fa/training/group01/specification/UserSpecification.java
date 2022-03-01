package com.fa.training.group01.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.fa.training.group01.entity.UserEntity;
import com.fa.training.group01.entity.UserEntity_;

@Component
public class UserSpecification {
	public Specification<UserEntity> containsEmail(String email) {
		return (root, query, cb) -> {
			return cb.like(root.get(UserEntity_.EMAIL), "%" + email + "%");
		};
	}
}
