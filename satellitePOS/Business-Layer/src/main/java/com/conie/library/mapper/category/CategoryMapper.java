package com.conie.library.mapper.category;

import java.util.ArrayList;
import java.util.List;

import com.conie.library.businesslogic.mapper.AuditTrailMapper;
import com.conie.library.dto.category.CategoryDto;
import com.conie.library.entity.category.Category;

public class CategoryMapper extends AuditTrailMapper {

	public static CategoryDto toDto(Category category) {
		if(category == null){
			return null;
		}
		CategoryDto categoryDto = new CategoryDto();
		toAuditTrailDto(categoryDto, category);
		categoryDto.setName(category.getName());
		return categoryDto;
	}
	
	public static Category toEntity(CategoryDto categoryDto){
		if(categoryDto == null){
			return null;
		}
		Category category = new Category();
		toAuditTrailEntity(categoryDto, category);	
		category.setName(categoryDto.getName());
		return category;
	}
	
	public static List<CategoryDto> toDtoList(List<Category> categories){
		List<CategoryDto> dtoList = new ArrayList<>();
		if (categories !=null){
			for (Category category : categories) {
				dtoList.add(toDto(category));
			}
		}
		return dtoList;
	}
}

