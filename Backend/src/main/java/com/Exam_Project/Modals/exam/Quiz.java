package com.Exam_Project.Modals.exam;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Quiz {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Long QId;
 
 private String title;
 
 private String description;
 
 private String maxMarks; 
 
 private String NumberOfQuestions;
 
 private boolean active =false;
 @ManyToOne(fetch = FetchType.EAGER)
 private Category category;
 @OneToMany(mappedBy = "quiz" ,cascade = CascadeType.ALL)
 @JsonIgnore
 private Set<Questions> questions=new HashSet<>();

public Quiz() {
	 
}

public Quiz(String title, String description, String maxMarks, String numberOfQuestions, boolean active,
		Category category, Set<Questions> questions) {
	super();
	this.title = title;
	this.description = description;
	this.maxMarks = maxMarks;
	NumberOfQuestions = numberOfQuestions;
	this.active = active;
	this.category = category;
	this.questions = questions;
}

public Quiz(Long qId, String title, String description, String maxMarks, String numberOfQuestions, boolean active) {
	super();
	QId = qId;
	this.title = title;
	this.description = description;
	this.maxMarks = maxMarks;
	NumberOfQuestions = numberOfQuestions;
	this.active = active;
}

public Long getQId() {
	return QId;
}

public void setQId(Long qId) {
	QId = qId;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getMaxMarks() {
	return maxMarks;
}

public void setMaxMarks(String maxMarks) {
	this.maxMarks = maxMarks;
}

public String getNumberOfQuestions() {
	return NumberOfQuestions;
}

public void setNumberOfQuestions(String numberOfQuestions) {
	NumberOfQuestions = numberOfQuestions;
}

public boolean isActive() {
	return active;
}

public void setActive(boolean active) {
	this.active = active;
}

public Category getCategory() {
	return category;
}

public void setCategory(Category category) {
	this.category = category;
}

public Set<Questions> getQuestions() {
	return questions;
}

public void setQuestions(Set<Questions> questions) {
	this.questions = questions;
}

 
 
}
