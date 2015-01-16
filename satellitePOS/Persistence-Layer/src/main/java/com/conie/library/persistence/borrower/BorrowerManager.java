package com.conie.library.persistence.borrower;

import javax.ejb.Local;

import com.conie.library.entity.borrower.Borrower;
import com.conie.library.manager.DataManager;

@Local
public interface BorrowerManager extends DataManager<Borrower> {
	
	public static final String NAME = "java:app/Persistence-Layer/BorrowerManagerBean";
	void updateTransaction(Borrower borrower);
	void createTransaction(Borrower borrower);
	

}
