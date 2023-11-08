package com.ty.hrmsspringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.hrmsspringboot.dao.UserDao;
import com.ty.hrmsspringboot.dto.ResponseStructure;
import com.ty.hrmsspringboot.entity.User;
import com.ty.hrmsspringboot.exceptions.UserNotfoundException;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {

		User userReceived = userDao.saveUser(user);

		ResponseStructure<User> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Data Saved Successfully");
		responseStructure.setData(userReceived);

		ResponseEntity<ResponseStructure<User>> entity = new ResponseEntity<ResponseStructure<User>>(responseStructure,
				HttpStatus.CREATED);
		return entity;
	}

	public ResponseEntity<ResponseStructure<User>> getUserById(int id) {

		User user = userDao.getUserById(id);

		if (user != null) {
			ResponseStructure<User> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Below Data Found for the Id " + id);
			responseStructure.setData(user);
			ResponseEntity<ResponseStructure<User>> entity = new ResponseEntity<ResponseStructure<User>>(
					responseStructure, HttpStatus.OK);
			return entity;
		} else {
			throw new UserNotfoundException("No user found with the given id");
		}
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(int id) {
		User user = userDao.getUserById(id);

		if (user != null) {
			user.setStatus(false);
			User userFound = userDao.updateUser(user);

			ResponseStructure<User> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("User with id " + id + " updated");
			responseStructure.setData(userFound);
			ResponseEntity<ResponseStructure<User>> entity = new ResponseEntity<ResponseStructure<User>>(
					responseStructure, HttpStatus.OK);
			return entity;
		} else {
			throw new UserNotfoundException("No user found with the given id to update");
		}
	}

	public ResponseEntity<ResponseStructure<User>> findUserByEmailAndPassword(String email, String password) {
		User user = userDao.findUserByEmailAndPassword(email, password);

		if (user != null) {
			ResponseStructure<User> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("User with given email and password is: ");
			responseStructure.setData(user);
			ResponseEntity<ResponseStructure<User>> entity = new ResponseEntity<ResponseStructure<User>>(
					responseStructure, HttpStatus.FOUND);
			return entity;
		} else {
			throw new UserNotfoundException("Invalid Credentials");
		}
	}

	public ResponseEntity<ResponseStructure<List<User>>> getAllTrainers() {
		List<User> trainers = userDao.getAllTrainers();

		if (!trainers.isEmpty()) {
			ResponseStructure<List<User>> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Below trainers are available in database: ");
			responseStructure.setData(trainers);
			ResponseEntity<ResponseStructure<List<User>>> entity = new ResponseEntity<ResponseStructure<List<User>>>(
					responseStructure, HttpStatus.FOUND);
			return entity;
		} else {
			throw new UserNotfoundException("No trainers are available");
		}
	}

	public ResponseEntity<ResponseStructure<User>> deleteUserById(int id) {
		User user = userDao.getUserById(id);

		if (user != null) {
			userDao.deleteUser(user);
			ResponseStructure<User> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("User with id " + id + " is deleted");
			ResponseEntity<ResponseStructure<User>> entity = new ResponseEntity<ResponseStructure<User>>(
					responseStructure, HttpStatus.OK);
			return entity;
		} else {
			throw new UserNotfoundException("No user found with the given id to delete");
		}
	}

	public ResponseEntity<ResponseStructure<List<User>>> getUserByStatus(boolean status) {
		List<User> users = userDao.getUserByStatus(status);
		
		if (users != null) {
			ResponseStructure<List<User>> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			if (status) {
				responseStructure.setMessage("User with Active status are: ");
			} else {
				responseStructure.setMessage("User with Inactive status are: ");
			}
			responseStructure.setData(users);
			ResponseEntity<ResponseStructure<List<User>>> entity = new ResponseEntity<ResponseStructure<List<User>>>(
					responseStructure, HttpStatus.OK);
			return entity;
		} else {
			throw new UserNotfoundException("No users found with the given status");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<User>>> getAllUsers(){
		List<User> users = userDao.getAllUsers();
		ResponseStructure<List<User>> responseStructure = new ResponseStructure<>();
		if (users != null) {
			//ResponseStructure<User> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Users present in db are: ");
			responseStructure.setData(users);
			ResponseEntity<ResponseStructure<List<User>>> entity = new ResponseEntity<ResponseStructure<List<User>>>(
					responseStructure, HttpStatus.OK);
			return entity;
		} else {
			responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
			responseStructure.setMessage("No users found in db");
			ResponseEntity<ResponseStructure<List<User>>> entity = new ResponseEntity<ResponseStructure<List<User>>>(
					responseStructure, HttpStatus.NO_CONTENT);
			return entity;
		}
	}
	
}
