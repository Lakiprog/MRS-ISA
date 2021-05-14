package com.MRSISA2021_T15.repository;

import com.MRSISA2021_T15.model.Allergy;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AllergyRepository extends CrudRepository<Allergy, Integer> {
	
	@Query("select a from Allergy a where patient.id = ?1")
	List<Allergy> findAllPatients(Integer id);
}
