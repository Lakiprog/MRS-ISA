package com.MRSISA2021_T15.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import com.MRSISA2021_T15.model.Appointment;
import com.MRSISA2021_T15.model.Patient;

@NoRepositoryBean
public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

	@Query("select distinct p from Appointment a join a.patient p where  p.username like '?1%' and APPOINTMENT_TYPE = 'APPOINTMENT_PHARMACIST'")
	public List<Patient> findAllByUsernamePharmacist(String startsWith);
	
	@Query("select distinct p from Appointment a join a.patient p where APPOINTMENT_TYPE = 'APPOINTMENT_PHARMACIST'")

	public List<Patient> findAllPharmacist();
	
	@Query("select distinct p from Appointment a join a.patient p")
	public List<Patient> findPatients();
	
	@Query("select distinct p from Appointment a join a.patient p where a.pharmacist = ?1")
	public List<Patient> findPatientsPharmacist(Integer id);
	
	public List<Appointment> findAll();
	
	@Query("select a from Appointment a where a.pharmacist.id = ?1")
	public List<Appointment> findAllPharmacistId(Integer id);
	
	@Query("select a from Appointment a where a.dermatologist.id = ?1")
	public List<Appointment> findAllDermatologistId(Integer id);
}
