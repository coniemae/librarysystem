package com.conie.library.dto.borrower;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.conie.library.dto.AuditTrailDto;
import com.conie.library.dto.book.BookDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BorrowerDto extends AuditTrailDto implements Serializable {

	private static final long serialVersionUID = 5322808787742175613L;
	
	private String name;
	private Date dateBorrowed;
	private Date dateReturned;
	private int dues;
	private BookDto book;
	
	public BorrowerDto(String name) {
		super();
		this.name = name;
	}
	
	public BorrowerDto() {
		super();
	}
	public BookDto getBook() {
		return book;
	}
	public Date getDateBorrowed() {
		return dateBorrowed;
	}

	public void setDateBorrowed(Date dateBorrowed) {
		this.dateBorrowed = dateBorrowed;
	}

	public void setBook(BookDto book) {
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

	
	public Date getDateReturned() {
		return dateReturned;
	}

	public void setDateReturned(Date dateReturned) {
		this.dateReturned = dateReturned;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((dateBorrowed == null) ? 0 : dateBorrowed.hashCode());
		result = prime * result
				+ ((dateReturned == null) ? 0 : dateReturned.hashCode());
		result = prime * result + dues;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BorrowerDto other = (BorrowerDto) obj;
		if (dateBorrowed == null) {
			if (other.dateBorrowed != null)
				return false;
		} else if (!dateBorrowed.equals(other.dateBorrowed))
			return false;
		if (dateReturned == null) {
			if (other.dateReturned != null)
				return false;
		} else if (!dateReturned.equals(other.dateReturned))
			return false;
		if (dues != other.dues)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BorrowerDto [name=" + name + ", dateBorrowed=" + dateBorrowed
				+ ", dateReturned=" + dateReturned + ", dues=" + dues + "]";
	}
}
