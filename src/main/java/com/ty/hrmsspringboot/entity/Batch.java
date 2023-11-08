package com.ty.hrmsspringboot.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Batch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@CreationTimestamp
	private LocalDateTime createdDateAndTime;
	@UpdateTimestamp
	private LocalDateTime completedDateAndTime;

	private boolean status;

	private String subject;
	
	@OneToMany  //(cascade = CascadeType.ALL)
	private List<Attendance> attendances;
}
