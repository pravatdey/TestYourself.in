package com.Exam_Project.service;

import java.util.Set;

import com.Exam_Project.Modals.exam.Questions;
import com.Exam_Project.Modals.exam.Quiz;

public interface QuestionService {
   
	public Questions addQuestions(Questions questions);
	
	public Questions updateQuestions(Questions questions);
	
	public Set<Questions> getQuestions();
	
	public Questions getQuestions(Long questionId);
	
	public Set<Questions> getQuestionsOfQuiz(Quiz quiz);
	
	public void deleteQuestion(Long questionId) ;
	
	public Questions get(Long questionId);
      
}
