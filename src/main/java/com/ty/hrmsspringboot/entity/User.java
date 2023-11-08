package com.ty.hrmsspringboot.entity;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "user_info")
@Getter
@Setter
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true, nullable = false)
	private String empId;
	private String name;
	@Column(unique = true, nullable = false)
	private String email;
	private long phoneNumber;
	private String password;
	private String role;
	private boolean status;

	@OneToMany //(cascade = CascadeType.ALL)
	private List<Batch> batches;

}
