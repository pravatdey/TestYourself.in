package com.Exam_Project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Exam_Project.Modals.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByUsername(String username);

	 
}
