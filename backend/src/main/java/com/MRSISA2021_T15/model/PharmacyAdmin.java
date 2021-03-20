package com.MRSISA2021_T15.model;

public class PharmacyAdmin extends User {
	private Pharmacy pharmacy;

	public PharmacyAdmin() {
		super();
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public PharmacyAdmin(int id, String email, String name, String surname, String adress, String city, String country,
			String phoneNumber, String username, String password) {
		super(id, email, name, surname, adress, city, country, phoneNumber, username, password);
	}
	
	public PharmacyAdmin(int id, String email, String name, String surname, String adress, String city, String country,
			String phoneNumber, String username, String password, Pharmacy pharmacy) {
		super(id, email, name, surname, adress, city, country, phoneNumber, username, password);
		this.pharmacy = pharmacy;
	}
}
