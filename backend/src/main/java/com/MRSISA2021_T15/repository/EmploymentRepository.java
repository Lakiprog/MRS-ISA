package com.MRSISA2021_T15.repository;

import com.MRSISA2021_T15.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmploymentRepository extends JpaRepository<Employment, Integer>{

	@Query("select e from Employment e where pharmacist.id = ?1")
	public EmploymentPharmacist findByPharmacistId(Integer id);
	
	@Query("select e from Employment e where dermatologist.id = ?1")
	public List<EmploymentDermatologist> findByDermatologistId(Integer id);
	
	@Query("select e from Employment e where EMPLOYMENT_TYPE = 'EMPLOYMENT_PHARMACIST'")
	public List<EmploymentPharmacist> findAllPharmacist();

	public List<Employment> findAll();

	public List<Employment> findAllByPharmacy(Optional<Pharmacy> pharmacy);

	public List<EmploymentDermatologist> findAllByDermatologist(Dermatologist dermatologist);

	public List<EmploymentPharmacist> findAllByPharmacist(Pharmacist pharmacist);







}
