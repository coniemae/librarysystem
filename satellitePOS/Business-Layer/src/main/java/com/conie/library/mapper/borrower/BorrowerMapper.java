package com.conie.library.mapper.borrower;

import com.conie.library.businesslogic.mapper.AuditTrailMapper;
import com.conie.library.dto.borrower.BorrowerDto;
import com.conie.library.entity.borrower.Borrower;
import com.conie.library.mapper.book.BookMapper;

public class BorrowerMapper extends AuditTrailMapper {

	public static BorrowerDto toDto(Borrower borrower) {
		if(borrower == null){
			return null;
		}
		BorrowerDto borrowerDto = new BorrowerDto();
		toAuditTrailDto(borrowerDto, borrower);
		borrowerDto.setName(borrower.getName());
		borrowerDto.setDateBorrowed(borrower.getDateBorrowed());
		borrowerDto.setDateReturned(borrower.getDateReturned());
		borrowerDto.setDues(borrower.getDues());
		bookToDto(borrower, borrowerDto);
		return borrowerDto;
	}

	public static Borrower toEntity(BorrowerDto borrowerDto) {
		if(borrowerDto == null){
			return null;
		}
		Borrower borrower = new Borrower();
		toAuditTrailEntity(borrowerDto, borrower);
		borrower.setName(borrowerDto.getName());
		borrower.setDateBorrowed(borrowerDto.getDateBorrowed());
		borrower.setDateReturned(borrowerDto.getDateReturned());
		borrower.setDues(borrowerDto.getDues());
		bookToEntity(borrowerDto, borrower);
		return borrower;
	}
	private static void bookToDto(Borrower borrower, BorrowerDto borrowerDto){
		borrowerDto.setBook(BookMapper.toDto(borrower.getBook()));
	}
	
	private static void bookToEntity(BorrowerDto borrowerDto, Borrower borrower){
		borrower.setBook(BookMapper.toEntity(borrowerDto.getBook()));
	}
}


