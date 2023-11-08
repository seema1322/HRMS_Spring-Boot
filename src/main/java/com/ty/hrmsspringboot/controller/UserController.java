package com.ty.hrmsspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.hrmsspringboot.dto.ResponseStructure;
import com.ty.hrmsspringboot.entity.User;
import com.ty.hrmsspringboot.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user){
		
		return userService.saveUser(user);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<User>>> getAllUsers(){
		
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<User>> getUserById(@PathVariable int id){
		return userService.getUserById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseStructure<User>> updateUserById(@PathVariable int id){
		return userService.updateUser(id);
	}
	
	@GetMapping("/{email}/{password}")
	public ResponseEntity<ResponseStructure<User>> getUserByEmailAndPassword(@PathVariable String email, @PathVariable("password") String password){
		return userService.findUserByEmailAndPassword(email, password);
	}
	
	@GetMapping("/trainers")
	public ResponseEntity<ResponseStructure<List<User>>> getAllTrainers(){
		return userService.getAllTrainers();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<User>> deleteUserById(@PathVariable int id){
		return userService.deleteUserById(id);
	}
}
