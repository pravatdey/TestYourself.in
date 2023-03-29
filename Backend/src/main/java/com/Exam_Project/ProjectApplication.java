package com.Exam_Project;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.Exam_Project.Modals.Role;
import com.Exam_Project.Modals.User;
import com.Exam_Project.Modals.UserRole;
import com.Exam_Project.service.UserService;

@SpringBootApplication

public class ProjectApplication implements CommandLineRunner {
	@Autowired
	private UserService userService; 
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	 
 

	@Override
	public void run(String... args) throws Exception {
	 System.out.println("Starting code");
	 
//	User user= new User();
//	user.setFirstName("Pravat");
//	user.setLastName("Dey");
//	user.setUsername("pravat123");
//	user.setPassword(this.bCryptPasswordEncoder.encode("abc"));
//	user.setEmail("pravat@gamil.com");
//	user.setProfile("default.png");
//	
//	Role role1=new Role();
//	role1.setRoleId(33L);
//	role1.setRoleName("Admin");
//	
//	Set<UserRole> userRoleSet=new HashSet<>();
//	UserRole userRole= new UserRole();
//	userRole.setRole(role1);
//	userRole.setUser(user);
//	
//	userRoleSet.add(userRole);
//	
//	User user1=this.userService.createUser(user, userRoleSet);
//	System.out.println(user1.getUsername());
//	 
		
	}
}