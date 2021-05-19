package com.MRSISA2021_T15.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "eReceipt_and_medicine_details")
public class EReceiptAndMedicineDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private EReceipt eReceipt;
	
	@ManyToOne
	private EReceiptMedicineDetails eReceiptMedicineDetails;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EReceipt geteReceipt() {
		return eReceipt;
	}

	public void seteReceipt(EReceipt eReceipt) {
		this.eReceipt = eReceipt;
	}

	public EReceiptMedicineDetails geteReceiptMedicineDetails() {
		return eReceiptMedicineDetails;
	}

	public void seteReceiptMedicineDetails(EReceiptMedicineDetails eReceiptMedicineDetails) {
		this.eReceiptMedicineDetails = eReceiptMedicineDetails;
	}
}
