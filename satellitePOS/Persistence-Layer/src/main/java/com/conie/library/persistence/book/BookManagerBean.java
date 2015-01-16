package com.conie.library.persistence.book;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.conie.library.entity.book.Book;
import com.conie.library.entity.category.Category;

@Stateless
public class BookManagerBean implements BookManager {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(Book book) {
		setCategoryReference(book);
		em.persist(book);
	}

	private void setCategoryReference(Book book) {
		if (book.getCategory() != null) {
			Integer bookCategoryId = book.getCategory().getId();
			if (bookCategoryId != null) {
				book.setCategory(em
						.getReference(Category.class, bookCategoryId));
			}
		}
	}

	@Override
	public void update(Book book) {
		setCategoryReference(book);
		em.merge(book);
	}

	@Override
	public void delete(int id) {
		Book book = em.find(Book.class, id);
		em.remove(book);
	}

	@Override
	public Book find(int id) {
		Book book = em.find(Book.class, id);
		initializeLazyloadedCategory(book);
		return book;
	}

	@Override
	public List<Book> findAll() {
		List<Book> books = em.createQuery("Select b from Book b", Book.class)
				.getResultList();
		initializeLazyloadedList(books);
		return books;
	}

	private void initializeLazyloadedList(List<Book> books) {
		for (Book book : books) {
			initializeLazyloadedCategory(book);
		}
	}

	private void initializeLazyloadedCategory(Book book) {
		if (book.getCategory() != null) {
			book.getCategory().getId();
		}
	}

}