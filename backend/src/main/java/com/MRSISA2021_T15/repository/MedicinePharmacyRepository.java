package com.MRSISA2021_T15.repository;
import com.MRSISA2021_T15.model.MedicinePharmacy;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MedicinePharmacyRepository extends CrudRepository<MedicinePharmacy, Integer> {
	
	@Query("select m from MedicinePharmacy m where pharmacy.id = ?1")
	public List<MedicinePharmacy> findByPharmacyId(Integer id);
}
