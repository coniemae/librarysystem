package com.conie.library.persistence.category;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.conie.library.entity.category.Category;

@Stateless
public class CategoryManagerBean implements CategoryManager {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void create(Category category) {
		em.persist(category);			
	}

	@Override
	public void update(Category entity) {
		em.merge(entity);
	}
	@Override
	public void delete(int id) {
		Category category = em.find(Category.class,id);
		em.remove(category);
	}
	@Override
	public Category find(int id) {
		Category category = em.find(Category.class, id);
		initializedBook(category);
		return category;
	}

	private void initializedBook(Category category) {
		if (category.getBook() != null){
		category.getBook().size();
		}
	}
	@Override
	public List<Category> findAll() {
		List<Category> categories = em.createNamedQuery("category.findall",
				Category.class).getResultList();
		initializedBookList(categories);
		return categories;
	}

	private void initializedBookList(List<Category> categories) {
		for (Category category : categories) {
			initializedBook(category);
		}
	}
}
