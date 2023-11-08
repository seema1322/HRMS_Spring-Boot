package com.ty.hrmsspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.hrmsspringboot.dto.ResponseStructure;
import com.ty.hrmsspringboot.entity.Batch;
import com.ty.hrmsspringboot.service.BatchService;

@RestController
@RequestMapping("/batches")
public class BatchController {
	
	@Autowired
	private BatchService batchService;
	
	@PostMapping("/{userid}")
	public ResponseEntity<ResponseStructure<Batch>> saveBatch(@RequestBody Batch batch, @PathVariable int userid){
		return batchService.saveBatch(batch, userid);
	}
	
	@GetMapping("/{batchid}")
	public ResponseEntity<ResponseStructure<Batch>> getBatchById(@PathVariable int batchid){
		return batchService.findBatchById(batchid);
	}

	@GetMapping("/users/{userid}")
	public ResponseEntity<ResponseStructure<List<Batch>>> getBatchByUserid(@PathVariable int userid){
		return batchService.findBatchByUserId(userid);
	}
	
	@PutMapping("/{batchid}")
	public ResponseEntity<ResponseStructure<Batch>> updateBatchStatus(@PathVariable int batchid){
		return batchService.updateBatchStatus(batchid);
	}
}
