package com.MRSISA2021_T15.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.model.PurchaseOrder;

@Repository
public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder, Integer> {

	PurchaseOrder findByOrderName(String orderName);
}
