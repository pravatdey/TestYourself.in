package com.Exam_Project.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Exam_Project.Modals.exam.Questions;
import com.Exam_Project.Modals.exam.Quiz;

public interface QuestionRepository extends JpaRepository<Questions, Long> {

	Set<Questions> findByQuiz(Quiz quiz);

}
