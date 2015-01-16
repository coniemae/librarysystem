package com.conie.library.businesslogic.borrower;

import com.conie.library.entity.book.Book;
import com.conie.library.entity.borrower.Borrower;
import com.conie.library.exception.GpsSatelliteException;

public interface IBorrow {

	void updateBookQty(Borrower borrower, Book book)
			throws GpsSatelliteException;

	void returnBook(Borrower borrower, Book book) throws GpsSatelliteException;

	void computeDues(Borrower borrower, Book book) throws GpsSatelliteException;
}
