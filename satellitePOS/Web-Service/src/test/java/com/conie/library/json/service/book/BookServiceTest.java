package com.conie.library.json.service.book;

import java.util.List;

import org.codehaus.jackson.type.TypeReference;
import org.junit.Ignore;
import org.junit.Test;

import com.conie.library.dto.book.BookDto;
import com.conie.library.dto.category.CategoryDto;
import com.conie.library.exception.GpsSatelliteException;
import com.conie.library.util.JsonClient;

public class BookServiceTest {

	private static final String CREATE = "http://localhost:8080/Web-Service/service/book/create";
	private static final String UPDATE = "http://localhost:8080/Web-Service/service/book/update";
	private static final String FIND = "http://localhost:8080/Web-Service/service/book/find";
	private static final String FIND_ALL = "http://localhost:8080/Web-Service/service/book/findall";
	private static final String DELETE = "http://localhost:8080/Web-Service/service/book/delete";
	private static final String FIND_CATEGORY = "http://localhost:8080/Web-Service/service/category/find";
	//private static final String UPDATE_CATEGORY = "http://localhost:8080/Web-Service/service/category/update";


	@Test
	@Ignore
	public void createBookTest() {
		BookDto book = new BookDto();
		book.setName("English Book");
		try {
			BookDto bookDto = (BookDto) JsonClient.getSecureJsonPostResponse(
					BookDto.class, CREATE, book);
			System.out.println("Added" + bookDto.toString());
		} catch (GpsSatelliteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void createISBN(){
		BookDto book = new BookDto();
		book.setIsbn("123");
		book.setName("Book with ISBN quantity and Date");
		book.setQuantity(2);
		try {
			BookDto bookDto = (BookDto) JsonClient.getSecureJsonPostResponse(BookDto.class, CREATE, book);
			System.out.println("Added!" + bookDto.toString());
		} catch (GpsSatelliteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void updateBookTest() {
		try {
			BookDto bookDto = (BookDto) JsonClient.getSecureJsonPostResponse(
					BookDto.class, FIND, 1);
			bookDto.setName("Science Book");
			bookDto = (BookDto) JsonClient.getSecureJsonPostResponse(
					BookDto.class, UPDATE, bookDto);
			System.out.println("Succesfully updated!");
		} catch (GpsSatelliteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void findBookTest() {
		try {
			BookDto bookDto = (BookDto) JsonClient.getSecureJsonPostResponse(
					BookDto.class, FIND, 1);
			System.out.println("------------------FOUND----------------");
			System.out.println(bookDto.toString());
		} catch (GpsSatelliteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void findAllBookTest() {
		try {
			List<BookDto> bookDto; 
			bookDto = (List<BookDto>) JsonClient.getSecureJsonPostResponseList(new TypeReference<List<BookDto>>() {
			}, FIND_ALL, "");
			for (BookDto dto : bookDto) {
				System.out.println(dto);
				System.out.println("Category : " + dto.getCategory().toString());
			}
		} catch (GpsSatelliteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
	}

	@Test
	@Ignore
	@SuppressWarnings({ "unused" })
	public void deleteBookTest() {
		try {
			BookDto bookDto = (BookDto) JsonClient.getSecureJsonPostResponse(
					BookDto.class, DELETE, 66);
			System.out.println("Succesfully deleted");
		} catch (GpsSatelliteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void createBookWithCategoryTest() {
		BookDto book = new BookDto();
		book.setName("Science Book");
		try {
			CategoryDto category = (CategoryDto) JsonClient
					.getSecureJsonPostResponse(CategoryDto.class,
							FIND_CATEGORY, 65);
			book.setCategory(category);
			@SuppressWarnings("unused")
			BookDto bookDto = (BookDto) JsonClient.getSecureJsonPostResponse(
					BookDto.class, CREATE, book);
		} catch (GpsSatelliteException e) {
			e.printStackTrace();
		}
	}
	

}
