package com.ty.hrmsspringboot.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Attendance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int numberOfStudent;
	@CreationTimestamp
	private LocalDateTime createdDateAndTime;

	@OneToOne //(cascade = CascadeType.ALL)
	private Image image;

}

