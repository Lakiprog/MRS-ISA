package com.MRSISA2021_T15.model;

import java.util.HashSet;
import java.util.Set;

public class Pharmacy {
	private String name, adress, city, country, description;
	private double rating;
	private Set<Pharmacist> pharmacists = new HashSet<Pharmacist>();
	private Set<Dermatologist> dermatologists = new HashSet<Dermatologist>();
	private Set<Medicine> medicines = new HashSet<Medicine>();
	private Set<AppointmentsDermatolog> appointments = new HashSet<AppointmentsDermatolog>();
	
	public Pharmacy() {
		
	}

	
	
	public Pharmacy(String name, String adress, String city, String country, String description, double rating,
			Set<Pharmacist> pharmacists, Set<Dermatologist> dermatologists, Set<Medicine> medicines,
			Set<AppointmentsDermatolog> appointments) {
		super();
		this.name = name;
		this.adress = adress;
		this.city = city;
		this.country = country;
		this.description = description;
		this.rating = rating;
		this.pharmacists = pharmacists;
		this.dermatologists = dermatologists;
		this.medicines = medicines;
		this.appointments = appointments;
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Set<Pharmacist> getPharmacists() {
		return pharmacists;
	}

	public void setPharmacists(Set<Pharmacist> pharmacists) {
		this.pharmacists = pharmacists;
	}

	public Set<Dermatologist> getDermatologists() {
		return dermatologists;
	}

	public void setDermatologists(Set<Dermatologist> dermatologists) {
		this.dermatologists = dermatologists;
	}

	public Set<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(Set<Medicine> medicines) {
		this.medicines = medicines;
	}

	public Set<AppointmentsDermatolog> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<AppointmentsDermatolog> appointments) {
		this.appointments = appointments;
	}
}
