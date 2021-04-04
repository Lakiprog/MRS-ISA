package com.MRSISA2021_T15.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.model.Medicine;

@Repository
public interface MedicineRepository extends CrudRepository<Medicine, Integer> {
	
	Medicine findByMedicineCode(String medicineCode);
	
	Medicine findByName(String name);
	
	@Query("select m.name from Medicine m")
	ArrayList<String> findAllName();
}
