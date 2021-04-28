package com.MRSISA2021_T15.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "SYSTEM_ADMIN")
public class SystemAdmin extends User{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "first_login")
	private boolean firstLogin;
	
	public SystemAdmin() {
		super();
	}

/*	public SystemAdmin(
			int id, String email, String name, String surname, String adress, String city, String country,
			String phoneNumber, String username, String password) {
		super(id, email, name, surname, adress, city, country, phoneNumber, username, password);
	}*/
	
	public boolean isFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}
	
}