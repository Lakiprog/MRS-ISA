package com.MRSISA2021_T15.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "SUPPLIER")
public class Supplier extends User{
	
	@Column(name = "first_login")
	private boolean firstLogin;
	// private Set<PurchaseOrder> purchaseOrders = new HashSet<PurchaseOrder>();

	/*public Set<PurchaseOrder> getPurchaseOrders() {
		return purchaseOrders;
	}

	public void setPurchaseOrders(Set<PurchaseOrder> purchaseOrders) {
		this.purchaseOrders = purchaseOrders;
	}*/

	public Supplier() {
		super();

	}

	public boolean isFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}

	/*public Supplier(int id, String email, String name, String surname, String adress, String city, String country,
			String phoneNumber, String username, String password) {
		super(id, email, name, surname, adress, city, country, phoneNumber, username, password);
	}*/
	
	/*public Supplier(int id, String email, String name, String surname, String adress, String city, String country,
			String phoneNumber, String username, String password, Set<PurchaseOrder> purchaseOrders) {
		super(id, email, name, surname, adress, city, country, phoneNumber, username, password);
		this.purchaseOrders = purchaseOrders;
	}*/
}
