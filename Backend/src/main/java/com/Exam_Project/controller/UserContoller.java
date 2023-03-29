 package com.Exam_Project.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Exam_Project.Modals.Role;
import com.Exam_Project.Modals.User;
import com.Exam_Project.Modals.UserRole;
import com.Exam_Project.helper.UserFoundException;
import com.Exam_Project.service.UserService;

@RestController
@RequestMapping("/{user}")
@CrossOrigin("*")
public class UserContoller {
    @Autowired
	private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	//creating user
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {
		
		user.setProfile("default.png");
		//Encoding password with bcryptpassword
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		
		Set<UserRole> roles=new HashSet<>();
		Role role=new Role();
		role.setRoleId(45L);
		role.setRoleName("NORMAL");
		
		UserRole role1=new UserRole();
		role1.setUser(user);
		role1.setRole(role);
		
		roles.add(role1);
		return this.userService.createUser(user, roles );
	}
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username")String username) {
		return this.userService.getUser(username);
	}
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId")long userId) {
		this.userService.deleteUser(userId);
	}
	 
	
}
