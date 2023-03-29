package com.Exam_Project.service;

import java.util.Set;

import com.Exam_Project.Modals.exam.Category;

public interface CategoryService {
   public Category addCategory(Category category);
   
   public Category updateCategory(Category category);
   
   public  Set<Category> getCategories();
   
   public Category getCategory(Long categoryId);
   
   public void deleteCategory(Long categoryId);
}
