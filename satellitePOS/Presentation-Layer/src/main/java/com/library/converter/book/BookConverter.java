package com.library.converter.book;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.conie.library.dto.book.BookDto;
import com.conie.library.exception.GpsSatelliteException;
import com.conie.library.util.JsonClient;


@FacesConverter(value = "bookConverter")
public class BookConverter implements Converter{

	private Logger LOGGER = Logger.getLogger(BookConverter.class);
	private static String FIND = "http://localhost:8080/Web-Service/service/book/find";
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1,
			String bookId) {
		if(StringUtils.isEmpty(bookId)|| !StringUtils.isNumeric(bookId)){
			return null;
		}else {
			return findBookbyId(bookId);			
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1,
			Object book) {
		@SuppressWarnings("rawtypes")
		Class bookDtoClass = BookDto.class;
		if (book!=null && bookDtoClass.equals(book.getClass())){
			BookDto bookDto = (BookDto) book;
			return bookDto.getId() + "";
		}
		return "";
	}
	
	private BookDto findBookbyId(String bookId) {
		try {
			Object response = JsonClient.getSecureJsonPostResponse(BookDto.class, FIND, Integer.valueOf(bookId));
			if (response != null) {
				return (BookDto) response;
			}
		} catch (GpsSatelliteException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
}
