package com.Exam_Project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Exam_Project.Modals.exam.Category;

public interface CategoryRepostitiry extends JpaRepository<Category, Long>{

}
