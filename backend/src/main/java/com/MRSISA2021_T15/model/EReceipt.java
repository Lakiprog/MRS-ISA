package com.MRSISA2021_T15.model;

import java.util.HashSet;
import java.util.Set;

public class EReceipt {
	double total;
	Set<Medicine> medicines = new HashSet<Medicine>();
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Set<Medicine> getMedicines() {
		return medicines;
	}
	public void setMedicines(Set<Medicine> medicines) {
		this.medicines = medicines;
	}
	public EReceipt(double total, Set<Medicine> medicines) {
		super();
		this.total = total;
		this.medicines = medicines;
	}
	
	
}
