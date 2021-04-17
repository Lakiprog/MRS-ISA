package com.MRSISA2021_T15.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pharmacy")
public class Pharmacy {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String name, adress, city, country, description;
	@Column
	private double rating;
	@Column
	double appointmentPrice;
	@JsonIgnore
	@OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Employment> employments;
	@JsonIgnore
	@OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Appointment> appointments;
	@JsonIgnore
	@OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<MedicinePharmacy> medicine;
	@JsonIgnore
	@OneToMany(mappedBy = "pharmacy", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<PharmacyAdmin> pharmacyAdmins = new HashSet<PharmacyAdmin>();;
	
	@Transient
	private ArrayList<Integer> pharmacyAdminsIds = new ArrayList<Integer>();
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<Employment> getEmployments() {
		return employments;
	}

	public void setEmployments(Set<Employment> employments) {
		this.employments = employments;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}
	
	public Set<MedicinePharmacy> getMedicine() {
		return medicine;
	}

	public void setMedicine(Set<MedicinePharmacy> medicine) {
		this.medicine = medicine;
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

	public Set<PharmacyAdmin> getPharmacyAdmins() {
		return pharmacyAdmins;
	}

	public void setPharmacyAdmins(Set<PharmacyAdmin> pharmacyAdmins) {
		this.pharmacyAdmins = pharmacyAdmins;
	}

	public ArrayList<Integer> getPharmacyAdminsIds() {
		return pharmacyAdminsIds;
	}

	public void setPharmacyAdminsIds(ArrayList<Integer> pharmacyAdminsIds) {
		this.pharmacyAdminsIds = pharmacyAdminsIds;
	}
}
