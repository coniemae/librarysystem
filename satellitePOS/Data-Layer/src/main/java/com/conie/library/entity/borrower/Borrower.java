package com.conie.library.entity.borrower;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.conie.library.entity.AuditTrail;
import com.conie.library.entity.book.Book;

@Entity
@NamedQueries(value = {@NamedQuery(name = "borrower.findAll" , query = "Select br FROM Borrower br")})
public class Borrower extends AuditTrail{

	private static final long serialVersionUID = -5206162059860952037L;
	
	private String name;
	private Date dateBorrowed;
	private Date dateReturned;
	private int dues;

	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	/*@ManyToOne(cascade = CascadeType.MERGE)*/
	private Book book;
	
	public Date getDateReturned() {
		return dateReturned;
	}
	public void setDateReturned(Date dateReturned) {
		this.dateReturned = dateReturned;
	}
	public Date getDateBorrowed() {
		return dateBorrowed;
	}
	public void setDateBorrowed(Date dateBorrowed) {
		this.dateBorrowed = dateBorrowed;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDues() {
		return dues;
	}
	public void setDues(int dues) {
		this.dues = dues;
	}
	@Override
	public String toString() {
		return "Borrower [name=" + name + ", dateBorrowed=" + dateBorrowed
				+ ", dateReturned=" + dateReturned + ", dues=" + dues + "]";
	}
}
