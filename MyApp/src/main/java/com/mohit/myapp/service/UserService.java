package com.mohit.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohit.myapp.modal.User;
import com.mohit.myapp.repository.UserRepository;

@Service("userService")
public class UserService {
	
	private UserRepository userRepository;
    
	@Autowired
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public void saveUser(User user) {
		userRepository.save(user);
	}
}
