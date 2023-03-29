package com.Exam_Project.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Exam_Project.Modals.exam.Questions;
import com.Exam_Project.Modals.exam.Quiz;
import com.Exam_Project.service.QuestionService;
import com.Exam_Project.service.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	@Autowired
	private QuizService quizService;
	 
	
	//add question
	@PostMapping("/")
	public ResponseEntity<Questions> add(@RequestBody Questions questions){
		return ResponseEntity.ok(this.questionService.addQuestions(questions));
	}
	
	//update question
	
	@PutMapping("/")
	public ResponseEntity<Questions> update(@RequestBody Questions questions){
		return ResponseEntity.ok(this.questionService.updateQuestions(questions));
	}
	
	//get all question of any quiz
	@GetMapping("/quiz/{qId}")
	public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("qId") Long qId){
		Quiz quiz=this.quizService.getQuiz(qId);
		Set<Questions>questions=quiz.getQuestions();
		List<Questions> list=new ArrayList<>(questions);
//		if(list.size()>Integer.parseInt(quiz.getNumberOfQuestions()));
//		{
//			list=list.subList(0,Integer.parseInt(quiz.getNumberOfQuestions()+1));
//		}
		
		list.forEach((q)->{q.setAnswer("");});
		Collections.shuffle(list);
		return ResponseEntity.ok(list);

		
//		 Quiz quiz=new Quiz();
//		 quiz.setQId(qId);
//		 Set<Questions> questionOfQuiz=this.questionService.getQuestionsOfQuiz(quiz);
//		 return ResponseEntity.ok(questionOfQuiz);
//		
	}
	//get Single question
	@GetMapping("/{questionId}")
	public Questions get(@PathVariable("questionId")Long questionId) {
		return this.questionService.getQuestions(questionId);
	}
	
	//delete question
	@DeleteMapping("/{questionId}")
	public void delete(@PathVariable("questionId") Long questionId) {
		this.questionService.deleteQuestion(questionId);
	}
	
	//evaluate Module
	@PostMapping("/evel-module")
	public ResponseEntity<?> evalModule(@RequestBody List<Questions> questions){
		System.out.println(questions);
		  double markGot=0;
		  int correctAnswer=0;
		  int attempted=0;
		   for(Questions q:questions){
			 Questions question=this.questionService.get(q.getQuestionId());
			 if(question.getAnswer().equals(q.getGivenAnswer())) {    
				 correctAnswer++;
				 
				 double marksSingle=Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
				 markGot+=marksSingle;
			 } 
			 if(q.getGivenAnswer()!=null || !q.getGivenAnswer().equals("")){
				       attempted++;
			 }
		}
		   Map<String,Object> map =Map.of("markGot",markGot,"correctAnswer",correctAnswer,"attempted",attempted);
		return ResponseEntity.ok(map);
	}

}
