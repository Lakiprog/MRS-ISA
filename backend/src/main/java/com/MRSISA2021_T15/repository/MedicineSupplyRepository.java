package com.MRSISA2021_T15.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.model.MedicineSupply;

@Repository
public interface MedicineSupplyRepository extends CrudRepository<MedicineSupply, Integer> {
	
	List<MedicineSupply> findAllBySupplierId(Integer supplierId);

}
