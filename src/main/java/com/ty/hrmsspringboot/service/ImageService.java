package com.ty.hrmsspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.hrmsspringboot.dao.AttendanceDao;
import com.ty.hrmsspringboot.dao.ImageDao;
import com.ty.hrmsspringboot.dto.ResponseStructure;
import com.ty.hrmsspringboot.entity.Attendance;
import com.ty.hrmsspringboot.entity.Image;
import com.ty.hrmsspringboot.exceptions.AttendanceNotFoundException;

@Service
public class ImageService {
	@Autowired
	private ImageDao imageDao;

	@Autowired
	private AttendanceDao attendanceDao;
	
	public ResponseEntity<ResponseStructure<Image>> saveImage(Image image, int attendanceId){
		Attendance attendance= attendanceDao.getAttendanceById(attendanceId);
		
		if (attendance != null && attendance.getImage()==null) {
			
			Image imageSaved= imageDao.saveImage(image);
			attendance.setImage(imageSaved);
			attendanceDao.updateAttendance(attendance);
			ResponseStructure<Image> responseStructure= new ResponseStructure<>();
			responseStructure.setData(imageSaved);
			responseStructure.setMessage("Image has been saved for the attendance with id "+attendanceId);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Image>>(responseStructure,HttpStatus.CREATED);
			
		} else {
			throw new AttendanceNotFoundException();
		}
		
	}
	
//	public ResponseEntity<ByteArrayResource> saveImage1(Image image, int attendanceId){
//		Attendance attendance= attendanceDao.getAttendanceById(attendanceId);
//		
//		if (attendance != null) {
//			
//			Image imageSaved= imageDao.saveImage(image);
//			attendance.setImage(imageSaved);
//			attendanceDao.updateAttendance(attendance);
//			
//			ByteArrayResource resource= new ByteArrayResource(imageSaved.getFile());
//			
//			return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=image.jpg")
//                    .body(resource);
//			
//		} else {
//			throw new AttendanceNotFoundException();
//		}
//		
//	}
}
