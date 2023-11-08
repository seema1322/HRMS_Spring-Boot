package com.ty.hrmsspringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.hrmsspringboot.dao.BatchDao;
import com.ty.hrmsspringboot.dao.UserDao;
import com.ty.hrmsspringboot.dto.ResponseStructure;
import com.ty.hrmsspringboot.entity.Batch;
import com.ty.hrmsspringboot.entity.User;
import com.ty.hrmsspringboot.exceptions.BatchNotFoundException;
import com.ty.hrmsspringboot.exceptions.UserNotfoundException;

@Service
public class BatchService {

	@Autowired
	private BatchDao batchDao;

	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<Batch>> saveBatch(Batch batch, int userId) {

		User user = userDao.getUserById(userId);

		if (user != null) {
			Batch batchReceived = batchDao.saveBatch(batch);
			user.getBatches().add(batch);
			userDao.saveUser(user);

			ResponseStructure<Batch> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Batch is assigned to " + user.getName());
			responseStructure.setData(batchReceived);
			return new ResponseEntity<ResponseStructure<Batch>>(responseStructure, HttpStatus.CREATED);
		} else {
			throw new UserNotfoundException();
		}
	}

	public ResponseEntity<ResponseStructure<Batch>> findBatchById(int batchid) {
		Batch batch = batchDao.getBatchById(batchid);

		if (batch != null) {
			ResponseStructure<Batch> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Batch details of id" + batchid + " are: ");
			responseStructure.setData(batch);
			return new ResponseEntity<ResponseStructure<Batch>>(responseStructure, HttpStatus.OK);
		} else {
			throw new BatchNotFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<List<Batch>>> findBatchByUserId(int userId) {
		List<Batch> batches = batchDao.getBatchByUserid(userId);

		if (!batches.isEmpty()) {
			ResponseStructure<List<Batch>> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Batch details of User with id " + userId);
			responseStructure.setData(batches);
			return new ResponseEntity<ResponseStructure<List<Batch>>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new BatchNotFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<Batch>> updateBatchStatus(int batchId) {
		Batch batch = batchDao.getBatchById(batchId);

		if (batch != null) {
			batch.setStatus(false);
			Batch updatedBatch = batchDao.updateBatchStatus(batch);
			ResponseStructure<Batch> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Batch status of id " + batchId + " is updated");
			responseStructure.setData(updatedBatch);
			return new ResponseEntity<ResponseStructure<Batch>>(responseStructure, HttpStatus.OK);
		} else {
			throw new BatchNotFoundException();
		}
	}
}
