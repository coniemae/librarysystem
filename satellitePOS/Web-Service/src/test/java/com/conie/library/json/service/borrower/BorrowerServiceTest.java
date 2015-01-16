package com.conie.library.json.service.borrower;

import java.util.List;

import org.codehaus.jackson.type.TypeReference;
import org.junit.Ignore;
import org.junit.Test;

import com.conie.library.dto.book.BookDto;
import com.conie.library.dto.borrower.BorrowerDto;
import com.conie.library.exception.GpsSatelliteException;
import com.conie.library.util.JsonClient;

public class BorrowerServiceTest {

	private static final String CREATE = "http://localhost:8080/Web-Service/service/borrower/create";
	private static final String DELETE = "http://localhost:8080/Web-Service/service/borrower/delete";
	private static final String UPDATE = "http://localhost:8080/Web-Service/service/borrower/update";
	private static final String UPDATE_TRANSACTION = "http://localhost:8080/Web-Service/service/transaction/update";
	private static final String FIND = "http://localhost:8080/Web-Service/service/borrower/find";
	private static final String FIND_BOOK = "http://localhost:8080/Web-Service/service/book/find";
	private static final String FIND_ALL = "http://localhost:8080/Web-Service/service/borrower/findAll";

	@Test
	@Ignore
	public void createBorrowerTest() {
		BorrowerDto borrower = new BorrowerDto("Conie123");
		try {
			JsonClient.getSecureJsonPostResponse(BookDto.class, CREATE,
					borrower);
		} catch (GpsSatelliteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void updateBorrowerTest() {
		BorrowerDto borrowerDto;
		try {
			borrowerDto = (BorrowerDto) JsonClient.getSecureJsonPostResponse(
					BorrowerDto.class, FIND, 15);
			borrowerDto.setName("Conie");
			borrowerDto = (BorrowerDto) JsonClient.getSecureJsonPostResponse(
					BorrowerDto.class, UPDATE, borrowerDto);
			System.out.println("Succesfully Edited");
		} catch (GpsSatelliteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void findBorrowerTest() {
		try {
			BorrowerDto borrowerDto = (BorrowerDto) JsonClient
					.getSecureJsonPostResponse(BorrowerDto.class, FIND, 3);
			System.out.println("FOUND BORROWER " + borrowerDto.toString());
		} catch (GpsSatelliteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void findAllBorrowerTest() {
		try {
			List<BorrowerDto> borrowerDto = (List<BorrowerDto>) JsonClient
					.getSecureJsonPostResponseList(
							new TypeReference<List<BorrowerDto>>() {
							}, FIND_ALL, "");
			for (BorrowerDto dto : borrowerDto) {
				System.out.println(dto);
			}
		} catch (GpsSatelliteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void deleteBorrowerTest() {
		try {
			JsonClient.getSecureJsonPostResponse(DELETE, 16);
			System.out.println("Deleted");
		} catch (GpsSatelliteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	//@Ignore
	public void borrowBookTest() {
		BorrowerDto borrowerDto;
		try {		
			BookDto bookDto = (BookDto) JsonClient.getSecureJsonPostResponse(
					BookDto.class, FIND_BOOK, 24);
			borrowerDto = (BorrowerDto) JsonClient
					.getSecureJsonPostResponse(BorrowerDto.class, FIND, 33);
			borrowerDto.setBook(bookDto);
			borrowerDto = (BorrowerDto) JsonClient
					.getSecureJsonPostResponse(BorrowerDto.class, UPDATE_TRANSACTION,
							borrowerDto);
			System.out.println("Updated!");
		} catch (GpsSatelliteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
