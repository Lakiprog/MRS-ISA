package com.MRSISA2021_T15.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "PHARMACY_ADMIN")
public class PharmacyAdmin extends User {
	
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "pharmacy_id")
	@NonNull
	private Pharmacy pharmacy;

	@JsonIgnore
	@OneToOne(mappedBy = "pharmacyAdmin", cascade = CascadeType.ALL)
	private PurchaseOrder purchaseOrder;

	public PharmacyAdmin() {
		super();
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}
}
