package com.MRSISA2021_T15.model;

import java.time.LocalDate;
import java.util.List;

public class EReceipt {

	private String eReceiptCode;
	
	private String patientName;
	
	private String patientSurname;
	
	private LocalDate issueDate;
	
	private List<EReceiptMedicineDetails> medicineDetails;

	public String geteReceiptCode() {
		return eReceiptCode;
	}

	public void seteReceiptCode(String eReceiptCode) {
		this.eReceiptCode = eReceiptCode;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientSurname() {
		return patientSurname;
	}

	public void setPatientSurname(String patientSurname) {
		this.patientSurname = patientSurname;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public List<EReceiptMedicineDetails> getMedicineDetails() {
		return medicineDetails;
	}

	public void setMedicineDetails(List<EReceiptMedicineDetails> medicineDetails) {
		this.medicineDetails = medicineDetails;
	}
}
