package com.MRSISA2021_T15.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue(value = "PATIENT")
public class Patient extends User{
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Appointment> appointments;
	@JsonIgnore
	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Allergy> allergies = new HashSet<Allergy>();
	
	@JsonIgnore
	@OneToMany(mappedBy= "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Complaint>complaints = new HashSet<Complaint>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Reservation> reservation;
	
	@Column
	private double collectedPoints;
	
	@Column
	private CategoryName categoryName;

	public Set<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(Set<Complaint> complaints) {
		this.complaints = complaints;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Set<Allergy> getAllergies() {
		return allergies;
	}

	public void setAllergies(Set<Allergy> allergies) {
		this.allergies = allergies;
	}
	
	/*public Patient(Set<Allergy> allergies) {
		super();
		this.allergies = allergies;
	}*/

	public double getCollectedPoints() {
		return collectedPoints;
	}

	public void setCollectedPoints(double collectedPoints) {
		this.collectedPoints = collectedPoints;
	}

	public CategoryName getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(CategoryName categoryName) {
		this.categoryName = categoryName;
	}

	public Patient() {
		super();
	}

	/*public Patient(int id, String email, String name, String surname, String adress, String city, String country,
			String phoneNumber, String username, String password) {
		super(id, email, name, surname, adress, city, country, phoneNumber, username, password);
	}*/

}
