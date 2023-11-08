package com.ty.hrmsspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.hrmsspringboot.dto.ResponseStructure;
import com.ty.hrmsspringboot.entity.Attendance;
import com.ty.hrmsspringboot.service.AttendanceService;

@RestController
@RequestMapping("/attendances")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;

	@PostMapping("/{batchid}")
	public ResponseEntity<ResponseStructure<Attendance>> saveAttendance(@RequestBody Attendance attendance,
			@PathVariable int batchid) {
		return attendanceService.saveAttendance(attendance, batchid);
	}

	@GetMapping("/{batchid}")
	public ResponseEntity<ResponseStructure<List<Attendance>>> findAttendanceByBatchId(@PathVariable int batchid) {
		return attendanceService.findAttendanceByBatchid(batchid);
	}
}
