package com.MRSISA2021_T15.repository;

import com.MRSISA2021_T15.model.Medicine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends CrudRepository<Medicine, Integer> {
	
	Medicine findByMedicineCode(String medicineCode);

}
