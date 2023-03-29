package com.Exam_Project.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Exam_Project.Modals.User;
import com.Exam_Project.Modals.UserRole;
import com.Exam_Project.helper.UserFoundException;
import com.Exam_Project.helper.UserNotFoundException;
import com.Exam_Project.repo.RoleRepository;
import com.Exam_Project.repo.UserRepository;
import com.Exam_Project.service.UserService;
@Service
public class UserServiceimpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception{
    	User local=this.userRepository.findByUsername(user.getUsername());
    	if(local!=null) {
    		System.out.println("User is already there !!");
    		throw new UserFoundException();
    	}else {
    		//user create
    		for(UserRole ur:userRoles) {
    			roleRepository.save(ur.getRole());
    		}
    		user.getUserRoles().addAll(userRoles);
    		local=this.userRepository.save(user);
    	 
    	}
 
	return local;
}
    //getting user by username
	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return this.userRepository.findByUsername(username);
	}
	@Override
	public void deleteUser(Long userId) {
		 this.userRepository.deleteById(userId);
		
	}
	 
 
}
