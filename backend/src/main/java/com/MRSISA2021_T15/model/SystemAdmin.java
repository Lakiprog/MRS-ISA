package com.MRSISA2021_T15.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue(value = "SYSTEM_ADMIN")
public class SystemAdmin extends User{
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy = "systemAdmin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Complaint> responsesToComplaints;
	
	public SystemAdmin() {
		super();
	}

/*	public SystemAdmin(
			int id, String email, String name, String surname, String adress, String city, String country,
			String phoneNumber, String username, String password) {
		super(id, email, name, surname, adress, city, country, phoneNumber, username, password);
	}*/
}