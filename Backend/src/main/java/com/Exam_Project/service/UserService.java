package com.Exam_Project.service;

import java.util.Set;

import com.Exam_Project.Modals.User;
import com.Exam_Project.Modals.UserRole;

public interface UserService {
	//creating user
	User createUser(User user, Set<UserRole> userRole) throws Exception;
	
	//get user by username
	public User getUser(String username);
	
	
	//delete user by id
	public void deleteUser(Long userId);
	 
	
	 
}
