package com.conie.library.webcontroller.category;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.codehaus.jackson.type.TypeReference;

import com.conie.library.dto.category.CategoryDto;
import com.conie.library.exception.GpsSatelliteException;
import com.conie.library.util.JsonClient;

@ManagedBean(name = "categoryController")
@ViewScoped
public class CategoryController implements Serializable {

	private static final long serialVersionUID = -4152600741102147696L;

	private static final Logger LOGGER = Logger
			.getLogger(CategoryController.class);
	private static final String CREATE = "http://localhost:8080/Web-Service/service/category/create";
	private static final String UPDATE = "http://localhost:8080/Web-Service/service/category/update";
	private static final String FIND_ALL = "http://localhost:8080/Web-Service/service/category/findAll";
	private static final String FIND = "http://localhost:8080/Web-Service/service/category/find";
	private static final String DELETE = "http://localhost:8080/Web-Service/service/category/delete";

	private CategoryDto category = new CategoryDto();
	private List<CategoryDto> categoryList = new ArrayList<CategoryDto>();
	/*private List<CategoryDto> filteredCategory = new ArrayList<CategoryDto>();*/
	private FacesMessage msg;
	private Action currentAction = Action.Create;
	private boolean addedOrUpdated;

	public enum Action {
		Create("Create Category"), Update("Update Category");
		private String text;

		Action(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	};

	public CategoryController() {
		init();
	}

	private void init() {
		initializeList();
		setCategory(new CategoryDto());
	}

	public void save() {
		if (Action.Create.equals(currentAction)) {
			create();
		} else if (Action.Update.equals(currentAction)) {
			update();
		}
		FacesContext.getCurrentInstance().addMessage(null, getMsg());
	}

	private void initializeList() {
		try {
			categoryList = findAllCategories();
		} catch (GpsSatelliteException e) {
			LOGGER.error(e);
		}
	}

	@SuppressWarnings("unchecked")
	private List<CategoryDto> findAllCategories() throws GpsSatelliteException {
		return (List<CategoryDto>) JsonClient.getSecureJsonPostResponseList(
				new TypeReference<List<CategoryDto>>() {
				}, FIND_ALL, "");
	}
	
	/*public void initCategoryList(){
		try {
			findAllCategories();
			filteredCategory.clear();
			filteredCategory.addAll(categoryList);
		} catch (GpsSatelliteException e) {
			LOGGER.error(e.getMessage(),e);
		}
		
	}*/

	public void create() {
		try {
			JsonClient.getSecureJsonPostResponse(CategoryDto.class, CREATE,
					category);
			setMsg(new FacesMessage(FacesMessage.SEVERITY_INFO,
					String.format("Category %s is successfully created!",
							category.getName()), category.getName()));
			initializeList();
			setAddedOrUpdated(true);
		} catch (GpsSatelliteException e) {
			LOGGER.error(e.getMessage(), e);
			setMsg(new FacesMessage(FacesMessage.SEVERITY_ERROR, String.format(
					"Failed to create category %s!", category.getName()),
					category.getName()));
		}
	}

	public void update() {
		try {
			JsonClient.getSecureJsonPostResponse(CategoryDto.class, UPDATE,
					category);
			setMsg(new FacesMessage(FacesMessage.SEVERITY_INFO,
					String.format("Category %s is successfully updated!",
							category.getName()), category.getName()));
			initializeList();
			setAddedOrUpdated(true);
		} catch (GpsSatelliteException e) {
			LOGGER.error(e.getMessage(), e);
			setMsg(new FacesMessage(FacesMessage.SEVERITY_ERROR, String.format(
					"Failed to update category %s !", category.getName()),
					category.getName()));
		}
	}

	public void delete() {
		try {
			JsonClient.getSecureJsonPostResponse(DELETE, category.getId());
			setMsg(new FacesMessage(FacesMessage.SEVERITY_INFO,
					String.format("Category %s is successfully deleted!",
							category.getName()), category.getName()));
			initializeList();
		} catch (GpsSatelliteException e) {
			LOGGER.error(e.getMessage(), e);
			setMsg(new FacesMessage(FacesMessage.SEVERITY_ERROR, String.format(
					"Failed to delete %s!", category.getName()),
					category.getName()));
		}
		

	}
	private CategoryDto find(int id) {
		try {
			return (CategoryDto) JsonClient.getSecureJsonPostResponse(
					CategoryDto.class, FIND, id);
		} catch (GpsSatelliteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void prepareUpdate() {
		currentAction = Action.Update;
		category = find(category.getId());
	}

	public void prepareCreate() {
		currentAction = Action.Create;
		category = new CategoryDto();
	}
	
	//-----------------------------------------Getter and Setter------------------------

	public CategoryDto getCategory() {
		return category;
	}

	/*public List<CategoryDto> getFilteredCategory() {
		return filteredCategory;
	}

	public void setFilteredCategory(List<CategoryDto> filteredCategory) {
		this.filteredCategory = filteredCategory;
	}*/

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public List<CategoryDto> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<CategoryDto> categoryList) {
		this.categoryList = categoryList;
	}

	public FacesMessage getMsg() {
		return msg;
	}

	public void setMsg(FacesMessage msg) {
		this.msg = msg;
	}

	public Action getCurrentAction() {
		return currentAction;
	}

	public void setCurrentAction(Action currentAction) {
		this.currentAction = currentAction;
	}

	public boolean isAddedOrUpdated() {
		return addedOrUpdated;
	}

	public void setAddedOrUpdated(boolean addedOrUpdated) {
		this.addedOrUpdated = addedOrUpdated;
	}

}
