package com.MRSISA2021_T15.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.model.MedicineSupply;

@Repository
public interface MedicineSupplyRepository extends CrudRepository<MedicineSupply, Integer> {
	
	List<MedicineSupply> findAllBySupplierId(Integer supplierId);
	
	@Query("select ms from PurchaseOrderMedicine pom left join MedicineSupply ms on pom.medicine.id = ms.medicine.id "
			+ "where pom.purchaseOrder.id = ?1 AND ms.supplier.id = ?2 AND ms.quantity < pom.quantity")
	List<MedicineSupply> hasNoMedicineInStock(Integer orderId, Integer supplierId);

}