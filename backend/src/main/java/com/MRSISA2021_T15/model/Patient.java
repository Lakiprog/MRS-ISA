package com.MRSISA2021_T15.model;

import java.util.HashSet;
import java.util.Set;

public class Patient extends User{
	Set<Allergy> allergies = new HashSet<Allergy>();

	public Set<Allergy> getAllergies() {
		return allergies;
	}

	public void setAllergies(Set<Allergy> allergies) {
		this.allergies = allergies;
	}

	public Patient(Set<Allergy> allergies) {
		super();
		this.allergies = allergies;
	}

	public Patient() {
		super();
	}

	public Patient(int id, String email, String name, String surname, String adress, String city, String country,
			String phoneNumber, String username, String password) {
		super(id, email, name, surname, adress, city, country, phoneNumber, username, password);
	}

}
