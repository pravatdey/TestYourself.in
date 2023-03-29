package com.Exam_Project.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Exam_Project.Modals.exam.Category;
import com.Exam_Project.Modals.exam.Quiz;

public interface QuizRepositiry extends JpaRepository<Quiz, Long> {
  public List<Quiz> findBycategory(Category category);
  
  public List<Quiz> findByActive(Boolean b);
  public List<Quiz> findByCategoryAndActive(Category c,Boolean b);
}
