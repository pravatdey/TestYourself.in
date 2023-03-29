package com.Exam_Project.controller;

import java.util.List;

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

import com.Exam_Project.Modals.exam.Category;
import com.Exam_Project.Modals.exam.Quiz;
import com.Exam_Project.service.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {
   @Autowired
   private QuizService quizService;
   
   
   //add quiz service
   @PostMapping("/")
   public ResponseEntity<Quiz> add(@RequestBody Quiz quiz){
	   return ResponseEntity.ok(this.quizService.addQuiz(quiz));
   }
   
   //update quiz
   @PutMapping("/")
   public ResponseEntity<Quiz> update(@RequestBody Quiz quiz){
	   return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
   }
   
   //get quiz
   @GetMapping("/")
   public ResponseEntity<?> quizzes(){
	   return ResponseEntity.ok(this.quizService.getQuizzes());
   }
   
   //get single quiz
   @GetMapping("/{qid}")
   public Quiz quiz(@PathVariable("qid")Long qid) {
	   return this.quizService.getQuiz(qid);
   }
   
   //delete the quiz
   
   @DeleteMapping("/{qid}")
   public void delete(@PathVariable("qid")Long qid) {
	   this.quizService.deleteQuiz(qid);
   }
   @GetMapping("/category/{cid}")
   public List<Quiz> getQuizesOfCategory(@PathVariable("cid") Long cid){
	   Category category=new Category();
	   category.setCid(cid);
	   return this.quizService.getQuizzesOfCategory(category);
   }
   //get Active Question Module
   @GetMapping("/active")
   public List<Quiz>getActiveQuizzes(){
	   return this.quizService.getActiveQuizzes();
   }
   
   @GetMapping("/category/active{cid}")
   public List<Quiz>getActiveQuizzes(@PathVariable("cid") Long cid){
	   Category category=new Category();
	   category.setCid(cid);
	   return this.quizService.getActiveQuizzesOfCategory(category);
	   
   }
}
