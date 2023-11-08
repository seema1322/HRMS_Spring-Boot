package com.ty.hrmsspringboot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.hrmsspringboot.entity.Attendance;
import com.ty.hrmsspringboot.repository.AttendanceRepository;

@Repository
public class AttendanceDao {

	@Autowired
	private AttendanceRepository attendanceRepository;

	public Attendance saveAttendance(Attendance attendance) {
		return attendanceRepository.save(attendance);
	}

	public Attendance getAttendanceById(int attendaceId) {
		Optional<Attendance> optional = attendanceRepository.findById(attendaceId);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public Attendance updateAttendance(Attendance attendance) {
		return attendanceRepository.save(attendance);
	}

	public List<Attendance> findAttendanceByBatchid(int batchid) {

		return attendanceRepository.findAttendance(batchid);
	}
}
