package com.conie.library.persistence.borrower;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.conie.library.entity.book.Book;
import com.conie.library.entity.borrower.Borrower;

@Stateless
public class BorrowerManagerBean implements BorrowerManager {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(Borrower entity) {
		setBookReference(entity);
		em.persist(entity);
	}
	@Override
	public void update(Borrower borrower){
		setBookReference(borrower);
		em.merge(borrower);
		}
	private void setBookReference(Borrower borrower) {
		if(borrower.getBook() !=null){
			Integer bookId = borrower.getBook().getId();
			if(bookId != null){
				Book bookReference = em.getReference(Book.class, bookId);
				borrower.setBook(bookReference);							
			}
		}
	}
	
	@Override
	public Borrower find(int id) {
		Borrower borrower = em.find(Borrower.class, id);
		return borrower;
	}

	@Override
	public List<Borrower> findAll() {
		List<Borrower> borrower = em.createNamedQuery("borrower.findAll",Borrower.class).getResultList();
		return borrower;
	}

	@Override
	public void delete(int id) {
		Borrower borrower = em.find(Borrower.class,id);
		em.remove(borrower);		
	}
	@Override
	public void updateTransaction(Borrower borrower) {
		em.merge(borrower);
	}
	@Override
	public void createTransaction(Borrower borrower) {
		em.persist(borrower);
	}
	

}
