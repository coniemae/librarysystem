package com.conie.library.manager;

import java.util.List;

public interface DataManager<T> {
	void create(T entity);
	void update(T entity);
	T find(int id);
	List<T> findAll();
	void delete(int id);
}
