package com.conie.library.entity.book;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.conie.library.entity.AuditTrail;
import com.conie.library.entity.borrower.Borrower;
import com.conie.library.entity.category.Category;


@Entity
/*@NamedQueries(value = {@NamedQuery(name = "book.findall", query = "Select b FROM Category b ")})*/
public class Book extends AuditTrail {

	public static final String FIND_ALL_BOOKS = "findAllBooks";
	private static final long serialVersionUID = 7202833798146309570L;

	private String name;
	private String isbn;
	private int quantity;

	@ManyToOne(cascade = CascadeType.DETACH)
	private Category category;
	
	@OneToMany(mappedBy = "book",cascade = CascadeType.MERGE)
	private List <Borrower> borrowers = new ArrayList<Borrower>();
	private Borrower borrower;
	
	public Borrower getBorrower() {
		return borrower;
	}

	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Borrower> getBorrowers() {
		return borrowers;
	}

	public void setBorrowers(List<Borrower> borrowers) {
		this.borrowers = borrowers;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Book [name=" + name + ", isbn=" + isbn + ", quantity="
				+ quantity + "]";
	}

}
