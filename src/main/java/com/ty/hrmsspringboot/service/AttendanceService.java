package com.ty.hrmsspringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.hrmsspringboot.dao.AttendanceDao;
import com.ty.hrmsspringboot.dao.BatchDao;
import com.ty.hrmsspringboot.dto.ResponseStructure;
import com.ty.hrmsspringboot.entity.Attendance;
import com.ty.hrmsspringboot.entity.Batch;
import com.ty.hrmsspringboot.exceptions.AttendanceNotFoundException;
import com.ty.hrmsspringboot.exceptions.BatchNotFoundException;

@Service
public class AttendanceService {
	@Autowired
	private AttendanceDao attendanceDao;

	@Autowired
	private BatchDao batchDao;

	public ResponseEntity<ResponseStructure<Attendance>> saveAttendance(Attendance attendance, int batchid) {
		Batch batch = batchDao.getBatchById(batchid);
		if (batch != null) {
			Attendance attendanceReceived = attendanceDao.saveAttendance(attendance);
			batch.getAttendances().add(attendanceReceived);
			batchDao.updateBatchStatus(batch);

			ResponseStructure<Attendance> responseStructure = new ResponseStructure<>();
			responseStructure.setData(attendanceReceived);
			responseStructure.setMessage("Attendance added for batch with id " + batchid);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());

			ResponseEntity<ResponseStructure<Attendance>> responseEntity = new ResponseEntity<ResponseStructure<Attendance>>(
					responseStructure, HttpStatus.CREATED);
			return responseEntity;
		} else {
			throw new BatchNotFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<List<Attendance>>> findAttendanceByBatchid(int batchid) {

		List<Attendance> attendances = attendanceDao.findAttendanceByBatchid(batchid);
		if (!attendances.isEmpty()) {
			ResponseStructure<List<Attendance>> responseStructure = new ResponseStructure<>();
			responseStructure.setData(attendances);
			responseStructure.setMessage("Below attendances are available for batch with id " + batchid);
			responseStructure.setStatusCode(HttpStatus.OK.value());
			ResponseEntity<ResponseStructure<List<Attendance>>> responseEntity = new ResponseEntity<ResponseStructure<List<Attendance>>>(
					responseStructure, HttpStatus.OK);
			return responseEntity;

		} else {
			throw new AttendanceNotFoundException();
		}
	}
}
