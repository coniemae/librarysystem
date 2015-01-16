package com.conie.library.dto.book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.conie.library.dto.AuditTrailDto;
import com.conie.library.dto.borrower.BorrowerDto;
import com.conie.library.dto.category.CategoryDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDto extends AuditTrailDto implements Serializable {

	private static final long serialVersionUID = -8713708145573441767L;
	private String name;
	private String isbn;
	private int quantity;
	private CategoryDto category;

	private List<BorrowerDto> borrowers = new ArrayList<BorrowerDto>();
	private BorrowerDto borrower;

	public BorrowerDto getBorrower() {
		return borrower;
	}

	public void setBorrower(BorrowerDto borrower) {
		this.borrower = borrower;
	}

	public List<BorrowerDto> getBorrowers() {
		return borrowers;
	}

	public void setBorrowers(List<BorrowerDto> borrowers) {
		this.borrowers = borrowers;
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

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + quantity;
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
		BookDto other = (BookDto) obj;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BookDto [name=" + name + ", isbn=" + isbn + ", quantity="
				+ quantity + "]";
	}
}
