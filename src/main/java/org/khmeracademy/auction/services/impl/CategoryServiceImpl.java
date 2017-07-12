package org.khmeracademy.auction.services.impl;

import java.util.ArrayList;

import org.khmeracademy.auction.entities.Category;
import org.khmeracademy.auction.entities.CategoryInputUpdate;
import org.khmeracademy.auction.filtering.CategoryFilter;
import org.khmeracademy.auction.repositories.CategoryRepository;
import org.khmeracademy.auction.services.CategoryService;
import org.khmeracademy.auction.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository cat;
	
	@Override
	public ArrayList<CategoryInputUpdate> findAllCategories(CategoryFilter filter, Pagination pagination) {
		pagination.setTotalCount(cat.count(filter));
		return cat.findAllCategories(filter, pagination);
	}
	
	@Override
	public ArrayList<Category> findMainCategories() {
		// TODO Auto-generated method stub
		return cat.findMainCategories();
	}
	

	@Override
	public ArrayList<Category> findCategoryByName(String category_name) {
		// TODO Auto-generated method stub
		return cat.findCategoryByName(category_name);
	}

	@Override
	public ArrayList<Category> findCategoryByParentCategory(String parent_name) {
		// TODO Auto-generated method stub
		return cat.findCategoryByParentCategory(parent_name);
	}

	@Override
	public ArrayList<Category> findCategoryByAnyField(String str_search) {
		// TODO Auto-generated method stub
		return cat.findCategoryByAnyField(str_search);
	}

	@Override
	public boolean addCategory(CategoryInputUpdate c) {
		// TODO Auto-generated method stub
		return cat.addCategory(c);
	}

	@Override
	public boolean updateCategory(CategoryInputUpdate c) {
		// TODO Auto-generated method stub
		return cat.updateCategory(c);
	}

	@Override
	public boolean deleteCategory(int category_id) {
		// TODO Auto-generated method stub
		return cat.deleteCategory(category_id);
	}

	@Override
	public CategoryInputUpdate findCategoryById(int category_id){
		return cat.findCategoryById(category_id);
	}

	
	
}
