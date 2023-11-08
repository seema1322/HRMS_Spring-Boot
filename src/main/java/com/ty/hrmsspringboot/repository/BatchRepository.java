package com.ty.hrmsspringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.hrmsspringboot.entity.Batch;

public interface BatchRepository extends JpaRepository<Batch, Integer>{
	@Query("select u.batches from User u where u.id=?1")
	List<Batch> findBatch(int userid);

}
