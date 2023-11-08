package com.ty.hrmsspringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.hrmsspringboot.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

	@Query("select b.attendances from Batch b where b.id=?1")
	List<Attendance> findAttendance(int batchid);
}
