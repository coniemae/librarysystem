package com.conie.library.businesslogic.borrower;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import com.conie.library.entity.book.Book;
import com.conie.library.entity.borrower.Borrower;
import com.conie.library.exception.GpsSatelliteException;

public class BookComputationTest {

	private IBorrow burrow = new BorrowerBusinessLogic();
	
	
	@Test
	@Ignore
	public void updateQty(){
		Borrower borrower = new Borrower();
		Book book = new Book();
		borrower.setBook(book);
		book.setQuantity(2);
		try {
			burrow.updateBookQty(borrower, book);
			Assert.assertEquals(0, book.getQuantity());
		} catch (GpsSatelliteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void returnBook() {
		Borrower borrower = new Borrower();
		Book book = new Book();
		borrower.setBook(book);
		book.setQuantity(1);
		try {
			burrow.returnBook(borrower, book);
			Assert.assertEquals(1, book.getQuantity());
		} catch (GpsSatelliteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void computeDues(){
		Borrower borrower = new Borrower();
		Book book = new Book();
		Date date = new Date(01/19/2015) ;
		borrower.setDateBorrowed(new Date());
		borrower.setDateReturned(date);
		borrower.setDues(borrower.getDues());
		try {
			burrow.computeDues(borrower, book);
			Assert.assertEquals(4, borrower.getDues());
		} catch (GpsSatelliteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
