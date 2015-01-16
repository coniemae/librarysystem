package com.conie.library.businesslogic.category;

import java.util.Date;
import java.util.List;

import com.conie.library.businesslogic.util.PersistenceEJBLookup;
import com.conie.library.dto.category.CategoryDto;
import com.conie.library.entity.category.Category;
import com.conie.library.mapper.category.CategoryMapper;
import com.conie.library.persistence.category.CategoryManager;

public class CategoryBusinessLogic {

	private CategoryManager manager = PersistenceEJBLookup.getCategoryManager();

	public CategoryDto create(CategoryDto categoryDto) {
		Category category = CategoryMapper.toEntity(categoryDto);
		category.setDateCreated(new Date());
		manager.create(category);
		return find(category.getId());
	}

	public CategoryDto update(CategoryDto categoryDto) {
		Category category = CategoryMapper.toEntity(categoryDto);
		category.setDateModified(new Date());
		manager.update(category);
		categoryDto = CategoryMapper.toDto(category);
		return categoryDto;
	}
	public CategoryDto find(int id) {
		Category category = manager.find(id);
		return CategoryMapper.toDto(category);
	}

	public void delete(int id) {
		Category category = new Category();
		category.setId(id);
		manager.delete(category.getId());
	}

	public List<CategoryDto> findAll() {
		List<Category> categoryList = manager.findAll();
		List<CategoryDto> dtoList = CategoryMapper.toDtoList(categoryList);
		return dtoList;
	}

}
