package org.khmeracademy.auction.entities;

import java.util.List;

public class Category {
	private int category_id;
	private String category_name;
	private String category_description;
	private Category parentCategory;
	private List<Category> subCategories;
	private String status;
	
	
	public int getCategory_id() {
		return category_id;
	}


	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}


	public String getCategory_name() {
		return category_name;
	}


	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}


	public String getCategory_description() {
		return category_description;
	}


	public void setCategory_description(String category_description) {
		this.category_description = category_description;
	}


	public Category getParentCategory() {
		return parentCategory;
	}


	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}


	public List<Category> getSubCategories() {
		return subCategories;
	}


	public void setSubCategories(List<Category> subCategories) {
		this.subCategories = subCategories;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Category [category_id=" + category_id + ", "
				+ (category_name != null ? "category_name=" + category_name + ", " : "")
				+ (category_description != null ? "category_description=" + category_description + ", " : "")
				+ (parentCategory != null ? "parentCategory=" + parentCategory + ", " : "") + "status=" + status + "]";
	}
	
}
