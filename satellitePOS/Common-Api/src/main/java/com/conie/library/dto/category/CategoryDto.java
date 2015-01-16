package com.conie.library.dto.category;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.conie.library.dto.AuditTrailDto;
import com.conie.library.dto.book.BookDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDto extends AuditTrailDto implements Serializable {

	private static final long serialVersionUID = -5962920672873010062L;
	
	private String name;
	
	private List<BookDto> book;
	
	public CategoryDto(String name) {
		super();
		this.name = name;
	}

	public CategoryDto() {
		super();
	}

	public List<BookDto> getBook() {
		return book;
	}

	public void setBook(List<BookDto> book) {
		this.book = book;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
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
		CategoryDto other = (CategoryDto) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CategoryDto [name=" + name + "]";
	}

}
