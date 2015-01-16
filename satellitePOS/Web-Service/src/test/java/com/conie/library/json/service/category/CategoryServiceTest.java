package com.conie.library.json.service.category;

import java.util.List;

import org.codehaus.jackson.type.TypeReference;
import org.junit.Ignore;
import org.junit.Test;

import com.conie.library.dto.category.CategoryDto;
import com.conie.library.exception.GpsSatelliteException;
import com.conie.library.util.JsonClient;

public class CategoryServiceTest {
	private static final String CREATE = "http://localhost:8080/Web-Service/service/category/create";
	private static final String UPDATE = "http://localhost:8080/Web-Service/service/category/update";
	private static final String FIND = "http://localhost:8080/Web-Service/service/category/find";
	private static final String FIND_ALL = "http://localhost:8080/Web-Service/service/category/findAll";
	private static final String DELETE = "http://localhost:8080/Web-Service/service/category/delete";

	@Test
	@Ignore
	public void createTest() {
		CategoryDto category = new CategoryDto("Science Category");
		try {
			JsonClient.getSecureJsonPostResponse(CategoryDto.class, CREATE,
					category);
			System.out.println("Added");
		} catch (GpsSatelliteException e) {
			e.printStackTrace();
		}
	}
	@Test
	@Ignore
	public void findTest() {
		try {
			CategoryDto categoryDto = (CategoryDto) JsonClient
					.getSecureJsonPostResponse(CategoryDto.class, FIND, 5);
			System.out.println("FOUND BOOK");
			System.out.println(categoryDto.toString());
		} catch (GpsSatelliteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void updateTest() {
		CategoryDto categoryDto;
		try {
			categoryDto = (CategoryDto) JsonClient.getSecureJsonPostResponse(
					CategoryDto.class, FIND, 5);
			categoryDto.setName("New Category Book");
			categoryDto = (CategoryDto) JsonClient.getSecureJsonPostResponse(
					CategoryDto.class, UPDATE, categoryDto);
			System.out.println("Sucessfully Edited!");
		} catch (GpsSatelliteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	@Ignore
	public void deleteTest() {
		try {
			JsonClient.getSecureJsonPostResponse(DELETE, 7);
			System.out.println("Deleted");
		} catch (GpsSatelliteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked" })
	@Test
	@Ignore
	public void findAllCategoryTest() {
		try {
			List<CategoryDto> categoryDto = (List<CategoryDto>) JsonClient
					.getSecureJsonPostResponseList(
							new TypeReference<List<CategoryDto>>() {
							}, FIND_ALL, "");
			System.out.println(categoryDto.toString());
		} catch (GpsSatelliteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}