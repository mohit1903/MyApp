package com.mohit.myapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mohit.myapp.modal.User;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByEmail(String email);
	User findByConfirmationToken(String confirmationToken);
    

}