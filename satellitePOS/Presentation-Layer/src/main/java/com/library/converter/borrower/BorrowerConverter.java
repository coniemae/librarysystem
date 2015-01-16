package com.library.converter.borrower;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.conie.library.dto.borrower.BorrowerDto;
import com.conie.library.exception.GpsSatelliteException;
import com.conie.library.util.JsonClient;

@FacesConverter(value = "borrowerConverter")
public class BorrowerConverter implements Converter{

		private Logger LOGGER = Logger.getLogger(BorrowerConverter.class);
		private static String FIND = "http://localhost:8080/Web-Service/service/borrower/find";
		
		@Override
		public Object getAsObject(FacesContext arg0, UIComponent arg1,
				String borrowerId) {
			if(StringUtils.isEmpty(borrowerId)|| !StringUtils.isNumeric(borrowerId)){
				return null;
			}else {
				return findBorrowerbyId(borrowerId);			
			}
		}

		@Override
		public String getAsString(FacesContext arg0, UIComponent arg1,
				Object borrower) {
			@SuppressWarnings("rawtypes")
			Class borrowerDtoClass = BorrowerDto.class;
			if (borrower!=null && borrowerDtoClass.equals(borrower.getClass())){
				BorrowerDto borrowerDto = (BorrowerDto) borrower;
				return borrowerDto.getId() + "";
			}
			return "";
		}
		
		private BorrowerDto findBorrowerbyId(String borrowerDto) {
			try {
				Object response = JsonClient.getSecureJsonPostResponse(BorrowerDto.class, FIND, Integer.valueOf(borrowerDto));
				if (response != null) {
					return (BorrowerDto) response;
				}
			} catch (GpsSatelliteException e) {
				LOGGER.error(e.getMessage(), e);
			}
			return null;
		}
	}

