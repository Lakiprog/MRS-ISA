package com.MRSISA2021_T15.model;

public class Dermatologist extends User{
	private boolean firstLogin;
	private double rating;

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Dermatologist() {
		super();
	}

	public Dermatologist(int id, String email, String name, String surname, String adress, String city, String country,
			String phoneNumber, String username, String password, boolean firstLogin, double rating) {
		super(id, email, name, surname, adress, city, country, phoneNumber, username, password);
		this.firstLogin = firstLogin;
		this.rating = rating;
	}

	public boolean isFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}
	
}
