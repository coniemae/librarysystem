package com.conie.library.persistence.book;

import javax.ejb.Local;

import com.conie.library.entity.book.Book;
import com.conie.library.manager.DataManager;

@Local
public interface BookManager extends DataManager<Book>{

	public static final String NAME = "java:app/Persistence-Layer/BookManagerBean";
	

	
}
