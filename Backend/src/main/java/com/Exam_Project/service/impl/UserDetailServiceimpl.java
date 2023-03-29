package com.Exam_Project.service.impl;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Exam_Project.Modals.User;
import com.Exam_Project.repo.UserRepository;
@Service
public class UserDetailServiceimpl implements UserDetailsService {
	@Autowired
   private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 
		User user=this.userRepository.findByUsername(username);
		if(user==null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("No user found");
		}
		return user;
	}
      
}
