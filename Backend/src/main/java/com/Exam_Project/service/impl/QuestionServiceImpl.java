package com.Exam_Project.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Exam_Project.Modals.exam.Questions;
import com.Exam_Project.Modals.exam.Quiz;
import com.Exam_Project.repo.QuestionRepository;
import com.Exam_Project.service.QuestionService;
@Service
public class QuestionServiceImpl implements QuestionService  {
	
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Questions addQuestions(Questions questions) {
		// TODO Auto-generated method stub
		return this.questionRepository.save(questions);
	}

	@Override
	public Questions updateQuestions(Questions questions) {
		// TODO Auto-generated method stub
		return this.questionRepository.save(questions);
	}

	@Override
	public Set<Questions> getQuestions() {
		// TODO Auto-generated method stub
		return new HashSet<>(this.questionRepository.findAll());
	}

	@Override
	public Questions getQuestions(Long questionId) {
		// TODO Auto-generated method stub
		return this.questionRepository.findById(questionId).get();
	}

	@Override
	public Set<Questions> getQuestionsOfQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.questionRepository.findByQuiz(quiz);
	}

	@Override
	public void deleteQuestion(Long questionId) {
		// TODO Auto-generated method stub
		Questions questions=new Questions();
		questions.setQuestionId(questionId);
		this.questionRepository.delete(questions);
		
	}

	@Override
	public Questions get(Long questionId) {
		// TODO Auto-generated method stub
		return this.questionRepository.getOne(questionId);
	}
	
	 

}
