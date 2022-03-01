package com.fa.training.group01.service;

public interface CRUDService<T, ID> {
	boolean insert(T entity);

	boolean update(T entity);

	boolean delete(T entity);

	T findOneByID(ID id);
}
