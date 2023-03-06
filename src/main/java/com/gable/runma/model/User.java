package com.gable.runma.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;


@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String password;
	private String fName;
	private String lName;

	@Column(unique = true)
	private String email;
	private String phone;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Temporal(TemporalType.DATE)
	private Date birthDate;
	private String address;
	private String country;
	private String province;
	private String subDistrict;
	private String district;
	private int postalCode;
    
	@OneToMany
	private List<Ticket> ticket;
}
