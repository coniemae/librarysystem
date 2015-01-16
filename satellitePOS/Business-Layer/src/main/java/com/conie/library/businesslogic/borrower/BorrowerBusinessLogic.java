package com.conie.library.businesslogic.borrower;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.conie.library.businesslogic.util.PersistenceEJBLookup;
import com.conie.library.dto.borrower.BorrowerDto;
import com.conie.library.entity.book.Book;
import com.conie.library.entity.borrower.Borrower;
import com.conie.library.exception.GpsSatelliteException;
import com.conie.library.mapper.borrower.BorrowerMapper;
import com.conie.library.persistence.book.BookManager;
import com.conie.library.persistence.borrower.BorrowerManager;

public class BorrowerBusinessLogic implements IBorrow {
	private final Logger LOGGER = Logger.getLogger("BorrowerBusinessLogic");
	private BorrowerManager manager = PersistenceEJBLookup.getBorrowerManager();
	private BookManager bookManager = PersistenceEJBLookup.getBookManager();

	public BorrowerDto create(BorrowerDto borrowerDto) {
		Borrower borrower = BorrowerMapper.toEntity(borrowerDto);
		borrower.setDateCreated(new Date());
		manager.create(borrower);
		return find(borrower.getId());
	}

	public void delete(int id) {
		manager.delete(id);
		/*
		 * Borrower borrower = new Borrower(); borrower.setId(id);
		 * manager.delete(borrower.getId());
		 */
	}

	public BorrowerDto find(int id) {
		Borrower borrower = manager.find(id);
		return BorrowerMapper.toDto(borrower);
	}

	public List<BorrowerDto> findAll() {
		List<Borrower> borrowerList = manager.findAll();
		/* List<BorrowerDto> dtoList = BorrowerMapper.toDtoList(borrowerList); */
		List<BorrowerDto> dtoList = new ArrayList<>();
		for (Borrower borrower : borrowerList) {
			dtoList.add(BorrowerMapper.toDto(borrower));
		}
		return dtoList;
	}

	public BorrowerDto update(BorrowerDto borrowerDto) {
		Borrower borrower = BorrowerMapper.toEntity(borrowerDto);
		borrower.setDateModified(new Date());
		manager.update(borrower);
		borrowerDto = BorrowerMapper.toDto(borrower);
		return borrowerDto;
	}

	public BorrowerDto updateTransaction(BorrowerDto borrowerDto)
			throws GpsSatelliteException {
		Borrower borrower = BorrowerMapper.toEntity(borrowerDto);
		Book findBook = bookManager.find(borrower.getBook().getId());
		updateBookQty(borrower, findBook);
		returnBook(borrower, findBook);
		computeDues(borrower, findBook);
		borrower.setDateModified(new Date());
		manager.updateTransaction(borrower);
		borrowerDto = BorrowerMapper.toDto(borrower);
		return borrowerDto;
	}

	public void updateBookQty(Borrower borrower, Book book)
			throws GpsSatelliteException {
		if (book != null) {
			if (borrower.getDateReturned() == null) {
				if (borrower.getBook().getQuantity() >= 1) {
					Integer bookQuantity = (borrower.getBook().getQuantity() - 1);
					borrower.getBook().setQuantity(bookQuantity);
					borrower.setDateBorrowed(new Date());
				} else {
					throw new GpsSatelliteException("Failed to Borrow Book.");
				}
			}
		}
	}

	@Override
	public void returnBook(Borrower borrower, Book book)
			throws GpsSatelliteException {
		if (book != null) {
			if (borrower.getDateReturned() != null) {
				if (borrower.getBook().getQuantity() >= 0) {
					Integer bookQuantity = (borrower.getBook().getQuantity() + 1);
					borrower.getBook().setQuantity(bookQuantity);
				} else {
					throw new GpsSatelliteException("Failed to return book");
				}
			}
		}
	}

	@Override
	public void computeDues(Borrower borrower, Book book) throws GpsSatelliteException {
		if (borrower.getDateReturned() != null) {
			int dateDiff = (int) ((borrower.getDateReturned().getTime() - borrower
					.getDateBorrowed().getTime()) / ((24 * 60 * 60 * 1000)));
			/* int diffHours = dateDiff / (60 * 60 * 1000) % 24; */
			borrower.setDues(dateDiff);
		}
	}
	/*
	 * * private void setbookQuantity(Borrower borrower){ Book findBook =
	 * bookManager.find(borrower.getBook().getId()); if (findBook != null) { if
	 * (borrower.getBook().getQuantity() >= 1 ){ Integer bookQuantity =
	 * (borrower.getBook().getQuantity() - 1);
	 * borrower.getBook().setQuantity(bookQuantity); }else{
	 * LOGGER.error("Failed to Borrow Book"); } } }
	 */
	public BorrowerDto createTransaction(BorrowerDto borrowerDto) {
		Borrower borrower = BorrowerMapper.toEntity(borrowerDto);
		borrower.setDateCreated(new Date());
		manager.create(borrower);
		return borrowerDto;
	}
}
