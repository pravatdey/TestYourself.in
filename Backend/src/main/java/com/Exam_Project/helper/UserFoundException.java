package com.Exam_Project.helper;

public class UserFoundException extends Exception{
	public UserFoundException() {
		super("User with the Username is already there in DB!! try Again");
	}
public UserFoundException(String msg) {
	super(msg);
}
}
