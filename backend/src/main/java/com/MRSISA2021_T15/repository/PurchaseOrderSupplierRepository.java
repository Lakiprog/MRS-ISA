package com.MRSISA2021_T15.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.model.PurchaseOrderSupplier;
import com.MRSISA2021_T15.model.Supplier;

@Repository
public interface PurchaseOrderSupplierRepository extends CrudRepository<PurchaseOrderSupplier, Integer> {

	@Query("select pos from PurchaseOrderSupplier pos where pos.purchaseOrder.id = ?1 and pos.supplier.id = ?2")
	PurchaseOrderSupplier findBySupplierIdAndPurchaseOrderId(Integer purchaseOrderId, Integer supplierId);
	
	List<PurchaseOrderSupplier> findBySupplier(Supplier supplier);
}