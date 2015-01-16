package com.conie.library.persistence.category;

import javax.ejb.Local;

import com.conie.library.entity.category.Category;
import com.conie.library.manager.DataManager;

@Local
public interface CategoryManager extends DataManager<Category> {
	
	public static final String NAME = "java:app/Persistence-Layer/CategoryManagerBean";

}
