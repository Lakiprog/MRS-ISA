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

}
