package com.MRSISA2021_T15.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.MRSISA2021_T15.model.Employment;
import com.MRSISA2021_T15.model.EmploymentDermatologist;
import com.MRSISA2021_T15.model.EmploymentPharmacist;

public interface EmploymentRepository extends JpaRepository<Employment, Integer>{

	@Query("select e from Employment e where pharmacist.id = ?1")
	public EmploymentPharmacist findByPharmacistId(Integer id);
	
	@Query("select e from Employment e where dermatologist.id = ?1")
	public List<EmploymentDermatologist> findByDermatologistId(Integer id);
}
