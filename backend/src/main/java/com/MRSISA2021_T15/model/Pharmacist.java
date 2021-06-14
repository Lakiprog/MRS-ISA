package com.MRSISA2021_T15.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue(value = "PHARMACIST")
public class Pharmacist extends User{

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@OneToMany(mappedBy = "pharmacist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<AppointmentPharmacist> appointments;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pharmacist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ComplaintPharmacist> complaints;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pharmacist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CanceledPharAppoinment> canceledAppointments;
	
	@Column
	private int numOfRating;
	
	public int getNumOfRating() {
		return numOfRating;
	}

	public void setNumOfRating(int numOfRating) {
		this.numOfRating = numOfRating;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Absence> absence;
	@Column(name = "rating")
	private double rating;
	@OneToOne(mappedBy = "pharmacist", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	private EmploymentPharmacist employments;

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Set<AppointmentPharmacist> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<AppointmentPharmacist> appointments) {
		this.appointments = appointments;
	}

	public Pharmacist() {
		super();
	}
}
