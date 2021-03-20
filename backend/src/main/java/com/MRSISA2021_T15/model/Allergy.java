package com.MRSISA2021_T15.model;

public class Allergy {
	private String medicineName, description;

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public Allergy(String medicineName, String description) {
		super();
		this.medicineName = medicineName;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
