package com.Exam_Project.service.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Exam_Project.Modals.exam.Category;
import com.Exam_Project.repo.CategoryRepostitiry;
import com.Exam_Project.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryRepostitiry categoryRepostitiry;

	@Override
	public Category addCategory(Category category) {
		 
		return this.categoryRepostitiry.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		 
		return this.categoryRepostitiry.save(category);
	}

	@Override
	public Set<Category> getCategories() {
		 
		return new LinkedHashSet<>( this.categoryRepostitiry.findAll());
	}

	@Override
	public Category getCategory(Long categoryId) {
		 
		return this.categoryRepostitiry.findById(categoryId).get();
	}

	@Override
	public void deleteCategory(Long categoryId) {
		 
		Category category = new Category();
		category.setCid(categoryId);
		this.categoryRepostitiry.delete(category);
		
	}

}
