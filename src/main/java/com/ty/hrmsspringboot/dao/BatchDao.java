package com.ty.hrmsspringboot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.hrmsspringboot.entity.Batch;
import com.ty.hrmsspringboot.repository.BatchRepository;

@Repository
public class BatchDao {
	
	@Autowired
	private BatchRepository batchRepository;
	
	public Batch saveBatch(Batch batch) {
		return batchRepository.save(batch);
	}
	
	public Batch getBatchById(int batchid) {
		Optional<Batch> optional = batchRepository.findById(batchid);
		
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public Batch updateBatchStatus(Batch batch) {
		return batchRepository.save(batch);
	}
	
	public List<Batch> getBatchByUserid(int userid){
		return batchRepository.findBatch(userid);
	}
}
