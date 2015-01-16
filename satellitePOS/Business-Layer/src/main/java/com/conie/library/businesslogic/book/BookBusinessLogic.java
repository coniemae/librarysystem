package com.conie.library.businesslogic.book;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.conie.library.businesslogic.util.PersistenceEJBLookup;
import com.conie.library.dto.book.BookDto;
import com.conie.library.entity.book.Book;
import com.conie.library.exception.GpsSatelliteException;
import com.conie.library.mapper.book.BookMapper;
import com.conie.library.persistence.book.BookManager;

public class BookBusinessLogic {

	private BookManager manager = PersistenceEJBLookup.getBookManager();

	public BookDto create(BookDto bookDto) throws GpsSatelliteException {
		Book book = BookMapper.toEntity(bookDto);
		//duplicate(bookDto);
		book.setDateCreated(new Date());
		manager.create(book);
		return find(book.getId());
	}

	/*public BookDto duplicate(BookDto bookDto) throws GpsSatelliteException {
		List<Book> bookList = manager.findAll();
		List<BookDto> books = new ArrayList<>();
		for (Book book : bookList) {
			if(books.contains(BookMapper.toDto(book))){
				throw new GpsSatelliteException("Already Added!");
			}
		}
		return bookDto;
	}*/

	public BookDto find(int id) {
		Book book = manager.find(id);
		return BookMapper.toDto(book);
	}

	public BookDto update(BookDto bookDto) {
		Book book = BookMapper.toEntity(bookDto);
		book.setDateModified(new Date());
		manager.update(book);
		bookDto = BookMapper.toDto(book);
		return bookDto;
	}

	public List<BookDto> findAll() {
		List<Book> bookList = manager.findAll();
		List<BookDto> books = new ArrayList<>();
		for (Book book : bookList) {
			books.add(BookMapper.toDto(book));
		}
		return books;
	}

	public void delete(int id) {
		manager.delete(id);
	}
}