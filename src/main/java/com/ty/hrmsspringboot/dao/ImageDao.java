package com.ty.hrmsspringboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.hrmsspringboot.entity.Image;
import com.ty.hrmsspringboot.repository.ImageRepository;

@Repository
public class ImageDao {
	
	@Autowired
	private ImageRepository imageRepository;
	
	public Image saveImage(Image image) {
		return imageRepository.save(image);
	}

}
