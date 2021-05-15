package com.MRSISA2021_T15.model;

import java.util.List;

public class EReceiptSearch {
	
	private Pharmacy pharmacy;
	
	private double total;
	
	private List<EReceiptMedicineDetails> eReceiptMedicineDetails;

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<EReceiptMedicineDetails> geteReceiptMedicineDetails() {
		return eReceiptMedicineDetails;
	}

	public void seteReceiptMedicineDetails(List<EReceiptMedicineDetails> eReceiptMedicineDetails) {
		this.eReceiptMedicineDetails = eReceiptMedicineDetails;
	}
}
