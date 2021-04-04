package com.MRSISA2021_T15.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "medicine")
public class Medicine {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String medicineCode, name, medicineType, form, composition, manufacturer, addtionalComments;
	@Column
	private boolean prescription;
	@OneToMany(mappedBy = "medicine", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<SubstituteMedicine> substituteMedicine = new HashSet<SubstituteMedicine>();
	@JsonIgnore
	@OneToMany(mappedBy = "medicine", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Allergy> allergies = new HashSet<Allergy>();
	@JsonIgnore
	@OneToMany(mappedBy = "medicine", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<MedicinePharmacy> medicinePharmacy;
	
	private ArrayList<Integer> substituteMedicineIds = new ArrayList<Integer>();

	
	public Set<MedicinePharmacy> getMedicine() {
		return medicinePharmacy;
	}

	public void setMedicine(Set<MedicinePharmacy> medicine) {
		this.medicinePharmacy = medicine;
	}

	public Set<Allergy> getAllergies() {
		return allergies;
	}

	public void setAllergies(Set<Allergy> allergies) {
		this.allergies = allergies;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMedicineType() {
		return medicineType;
	}

	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMedicineCode() {
		return medicineCode;
	}

	public void setMedicineCode(String medicineCode) {
		this.medicineCode = medicineCode;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getAddtionalComments() {
		return addtionalComments;
	}

	public void setAddtionalComments(String addtionalComments) {
		this.addtionalComments = addtionalComments;
	}

	public boolean isPrescription() {
		return prescription;
	}

	public void setPrescription(boolean prescription) {
		this.prescription = prescription;
	}

	public Set<SubstituteMedicine> getSubstituteMedicine() {
		return substituteMedicine;
	}

	public void setSubstituteMedicine(Set<SubstituteMedicine> substituteMedicine) {
		this.substituteMedicine = substituteMedicine;
	}

	public ArrayList<Integer> getSubstituteMedicineIds() {
		return substituteMedicineIds;
	}

	public void setSubstituteMedicineIds(ArrayList<Integer> substituteMedicineIds) {
		this.substituteMedicineIds = substituteMedicineIds;
	}
}
