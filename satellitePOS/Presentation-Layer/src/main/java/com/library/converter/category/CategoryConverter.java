package com.library.converter.category;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.conie.library.dto.category.CategoryDto;
import com.conie.library.exception.GpsSatelliteException;
import com.conie.library.util.JsonClient;
@FacesConverter(value = "categoryConverter")
public class CategoryConverter implements Converter{
	private Logger LOGGER = Logger.getLogger(CategoryConverter.class);
	private static String FIND = "http://localhost:8080/Web-Service/service/category/find";
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String categoryId) {
		if(StringUtils.isEmpty(categoryId)||!StringUtils.isNumeric(categoryId)){
			return null;			
		}else {
			return findCategorybyId(categoryId);
		}
	}
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object category) {
		@SuppressWarnings("rawtypes")
		Class categoryDtoClass = CategoryDto.class;
		if (category !=null && categoryDtoClass.equals(category.getClass())){
			CategoryDto categoryDto = (CategoryDto) category;
			return categoryDto.getId() + "";
		}
		return "";
	}
	private CategoryDto findCategorybyId(String categoryId) {
		try {
			Object response = JsonClient.getSecureJsonPostResponse(CategoryDto.class, FIND, Integer.valueOf(categoryId));
			if (response != null) {
				return (CategoryDto) response;
			}
		} catch (GpsSatelliteException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
}
