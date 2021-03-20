package com.MRSISA2021_T15.model;

public class SystemAdmin extends User{

	public SystemAdmin() {
		super();
	}

	public SystemAdmin(int id, String email, String name, String surname, String adress, String city, String country,
			String phoneNumber, String username, String password) {
		super(id, email, name, surname, adress, city, country, phoneNumber, username, password);
	}
	
}
