package com.conie.library.businesslogic.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.conie.library.persistence.book.BookManager;
import com.conie.library.persistence.borrower.BorrowerManager;
import com.conie.library.persistence.category.CategoryManager;

public class PersistenceEJBLookup {

	private static final Logger LOGGER = Logger
			.getLogger(PersistenceEJBLookup.class);

	public static BookManager getBookManager() {
		Object bean = lookup(BookManager.NAME);
		if (bean == null) {
			return null;
		}
		return (BookManager) bean;
	}

	public static CategoryManager getCategoryManager() {
		Object bean = lookup(CategoryManager.NAME);
		if (bean == null) {
			return null;
		}
		return (CategoryManager) bean;
	}

	public static BorrowerManager getBorrowerManager() {
		Object bean = lookup(BorrowerManager.NAME);
		if (bean == null) {
			return null;
		}
		return (BorrowerManager) bean;
	}

	private static synchronized Object lookup(String name) {
		try {
			InitialContext init = new InitialContext();
			return init.lookup(name);
		} catch (NamingException e) {
			LOGGER.info(e);
		}
		return null;
	}

}
