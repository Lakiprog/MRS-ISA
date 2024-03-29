package com.MRSISA2021_T15.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "purchase_order")
public class PurchaseOrder {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	@NonNull
	private String purchaseOrderName;
	
	@JsonIgnore
	@Transient
	@OneToMany(mappedBy = "purchase_order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<PurchaseOrderMedicine> purchaseOrderMedicine = new HashSet<PurchaseOrderMedicine>();
	
	@JsonIgnore
	@Transient
	@OneToMany(mappedBy = "purchase_order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<PurchaseOrderSupplier> purchaseOrderSupplier = new HashSet<PurchaseOrderSupplier>();

	@ManyToOne
	@JoinColumn(name="pharmacy_id")
	@NonNull
	private Pharmacy pharmacy;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pharmacy_admin_id")
	private PharmacyAdmin pharmacyAdmin;

	@Transient
	private ArrayList<Integer> substituteMedicineIds = new ArrayList<Integer>();

	@Column
	@NonNull
	private LocalDate dueDateOffer;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPurchaseOrderName() {
		return purchaseOrderName;
	}

	public void setPurchaseOrderName(String purchaseOrderName) {
		this.purchaseOrderName = purchaseOrderName;
	}

	public Set<PurchaseOrderMedicine> getPurchaseOrderMedicine() {
		return purchaseOrderMedicine;
	}

	public void setPurchaseOrderMedicine(Set<PurchaseOrderMedicine> purchaseOrderMedicine) {
		this.purchaseOrderMedicine = purchaseOrderMedicine;
	}
	
	public Set<PurchaseOrderSupplier> getPurchaseOrderSupplier() {
		return purchaseOrderSupplier;
	}

	public void setPurchaseOrderSupplier(Set<PurchaseOrderSupplier> purchaseOrderSupplier) {
		this.purchaseOrderSupplier = purchaseOrderSupplier;
	}

	public LocalDate getDueDateOffer() {
		return dueDateOffer;
	}

	public void setDueDateOffer(LocalDate dueDateOffer) {
		this.dueDateOffer = dueDateOffer;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public PharmacyAdmin getPharmacyAdmin() {
		return pharmacyAdmin;
	}

	public void setPharmacyAdmin(PharmacyAdmin pharmacyAdmin) {
		this.pharmacyAdmin = pharmacyAdmin;
	}

	public ArrayList<Integer> getSubstituteMedicineIds() {
		return substituteMedicineIds;
	}

	public void setSubstituteMedicineIds(ArrayList<Integer> substituteMedicineIds) {
		this.substituteMedicineIds = substituteMedicineIds;
	}
}
