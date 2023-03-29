package com.Exam_Project.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Exam_Project.Modals.JwtRequset;
import com.Exam_Project.Modals.User;
import com.Exam_Project.Modals.jwtResponse;
import com.Exam_Project.config.JwtUtil;
import com.Exam_Project.helper.UserNotFoundException;
import com.Exam_Project.service.impl.UserDetailServiceimpl;

@RestController
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
	private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailServiceimpl userDetailService;
    @Autowired
    private JwtUtil jwtutil;
    
    //generate token
    @PostMapping("/{generate-token}")
    public ResponseEntity<?>generateToken(@RequestBody JwtRequset jwtRequest) throws Exception{
    	try {
    		authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
    	}catch (UserNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User not found");
		}
    	
    UserDetails userDetails=this.userDetailService.loadUserByUsername(jwtRequest.getUsername());
       String token=this.jwtutil.generateToken(userDetails);
       return ResponseEntity.ok(new jwtResponse(token));
    }
    
	private void authenticate(String username,String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}catch (DisabledException e) {
			 throw new Exception("USER DISABLED" +e.getMessage());
		}catch (BadCredentialsException e) {
			 throw new Exception("Invalid Creadentials" +e.getMessage());
		}
	}
	//return the details of current user
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal)
    {
	 return ((User)this.userDetailService.loadUserByUsername(principal.getName()));
    
    }
}
