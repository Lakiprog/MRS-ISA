package com.MRSISA2021_T15.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "substitute_medicine")
public class SubstituteMedicine {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "medicine_id")
	private Medicine medicine;
	
	@ManyToOne
	@JoinColumn(name = "substitute_medicine_id")
	private Medicine substituteMedicine;

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public Medicine getSubstituteMedicine() {
		return substituteMedicine;
	}

	public void setSubstituteMedicine(Medicine substituteMedicine) {
		this.substituteMedicine = substituteMedicine;
	}
}
