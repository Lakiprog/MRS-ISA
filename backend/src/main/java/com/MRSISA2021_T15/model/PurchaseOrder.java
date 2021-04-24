package com.MRSISA2021_T15.model;

import java.time.LocalDateTime;
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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "purchase_order")
public class PurchaseOrder {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String orderName;
	
	@JsonIgnore
	@Transient
	@OneToMany(mappedBy = "purchase_order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<PurchaseOrderMedicine> purchaseOrderMedicine = new HashSet<PurchaseOrderMedicine>();
	
	@JsonIgnore
	@Transient
	@OneToMany(mappedBy = "purchase_order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<PurchaseOrderSupplier> purchaseOrderSupplier = new HashSet<PurchaseOrderSupplier>();
	
	@Column
	private LocalDateTime dueDateOffer;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
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

	public LocalDateTime getDueDateOffer() {
		return dueDateOffer;
	}

	public void setDueDateOffer(LocalDateTime dueDateOffer) {
		this.dueDateOffer = dueDateOffer;
	}
}
