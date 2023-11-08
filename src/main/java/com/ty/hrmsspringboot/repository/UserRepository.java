package com.ty.hrmsspringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.hrmsspringboot.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	
	User findByEmailAndPassword(String email, String password);

	@Query("select u from User u where u.role=?1")
	List<User> finByRole(String role);
	
	@Query("select u from User u where u.role=:status")
	List<User> finByStatus(boolean status);
}
