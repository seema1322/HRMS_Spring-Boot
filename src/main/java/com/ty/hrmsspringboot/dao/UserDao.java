package com.ty.hrmsspringboot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.hrmsspringboot.entity.User;
import com.ty.hrmsspringboot.repository.UserRepository;

@Repository
public class UserDao {
	
	@Autowired
	private UserRepository userRepository;
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public User getUserById(int id) {
		Optional<User> optional = userRepository.findById(id);
		
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public User updateUser(User user) {
		
		return userRepository.save(user);
	}
	
	public User findUserByEmailAndPassword(String email, String password) {
		
		return userRepository.findByEmailAndPassword(email, password);
	}
	
	public List<User> getAllTrainers(){
		
		return userRepository.finByRole("trainer");
	}
	
	public void deleteUser(User user) {
		userRepository.delete(user);
	}
	
	public List<User> getUserByStatus(boolean status){
		return userRepository.finByStatus(status);
	}
	
	public List<User> getAllUsers(){
		
		return userRepository.findAll();
	}
}
