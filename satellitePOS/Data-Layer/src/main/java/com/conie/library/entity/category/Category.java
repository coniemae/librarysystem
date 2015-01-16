package com.conie.library.entity.category;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.conie.library.entity.AuditTrail;
import com.conie.library.entity.book.Book;

@Entity
@NamedQueries(value = {@NamedQuery(name = "category.findall", query = "Select c FROM Category c ")})
public class Category extends AuditTrail{

	private static final long serialVersionUID = -410359234363000422L;
	
	private String name;
	
	@OneToMany(mappedBy = "category",cascade = CascadeType.DETACH)
	private List <Book> book;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Book> getBook() {
		return book;
	}
	public void setBook(List<Book> book) {
		this.book = book;
	}
	
	
}
