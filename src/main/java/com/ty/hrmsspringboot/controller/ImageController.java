package com.ty.hrmsspringboot.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ty.hrmsspringboot.dto.ResponseStructure;
import com.ty.hrmsspringboot.entity.Image;
import com.ty.hrmsspringboot.service.ImageService;

import jakarta.servlet.annotation.MultipartConfig;

@RestController
@MultipartConfig
public class ImageController {

	@Autowired
	private ImageService imageService;

	@PostMapping(value = "/images/{attendanceid}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseStructure<Image>> saveImage(@RequestBody MultipartFile image,
			@PathVariable int attendanceid) {

		if (!image.isEmpty()) {
			try {

				byte[] file = image.getBytes();
				Image image2 = new Image();
				image2.setFile(file);
				return imageService.saveImage(image2, attendanceid);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;

	}
	
//	@PostMapping(value = "/saving/{attendanceid}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {
//			MediaType.MULTIPART_FORM_DATA_VALUE })
//	public ResponseEntity<ByteArrayResource> saveImage1(@RequestBody MultipartFile image,
//			@PathVariable int attendanceid) {
//
//		if (!image.isEmpty()) {
//			try {
//
//				byte[] file = image.getBytes();
//				Image image2 = new Image();
//				image2.setFile(file);
//				return imageService.saveImage1(image2, attendanceid);
//
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return null;
//
//	}
}
