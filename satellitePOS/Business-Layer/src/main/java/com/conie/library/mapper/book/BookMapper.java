package com.conie.library.mapper.book;

import com.conie.library.businesslogic.mapper.AuditTrailMapper;
import com.conie.library.dto.book.BookDto;
import com.conie.library.entity.book.Book;
import com.conie.library.mapper.category.CategoryMapper;

public class BookMapper extends AuditTrailMapper {

	public static BookDto toDto(Book book) {		
		if(book==null){
			return null;
		}
		BookDto bookDto = new BookDto();
		toAuditTrailDto(bookDto, book);
		bookDto.setName(book.getName());
		bookDto.setIsbn(book.getIsbn());
		bookDto.setQuantity(book.getQuantity());
		categoryToDto(book, bookDto);
		return bookDto;
	}

	public static Book toEntity(BookDto bookDto) {
		if(bookDto == null){
			return null;
		}
		Book book = new Book();
		toAuditTrailEntity(bookDto, book);
		book.setName(bookDto.getName());
		book.setIsbn(bookDto.getIsbn());
		book.setQuantity(bookDto.getQuantity());
		categoryToEntity(bookDto, book);
		return book;
	}

	private static void categoryToDto(Book book, BookDto bookDto) {
		bookDto.setCategory(CategoryMapper.toDto(book.getCategory()));
	}
	private static void categoryToEntity(BookDto bookDto, Book book) {
		book.setCategory(CategoryMapper.toEntity(bookDto.getCategory()));
	}
	
}
