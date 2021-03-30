package com.MRSISA2021_T15.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import com.MRSISA2021_T15.model.Appointment;
import com.MRSISA2021_T15.model.Patient;

@NoRepositoryBean
public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
	@Query("select distinct a, p from Appointment a join fetch a.patient p where  p.username like ?1 + '%' and APPOINTMENT_TYPE = 'AppointmentPharmacist'")
	public List<Patient> findAllByUsernamePharmacist(String startsWith);
	
	@Query("select distinct a, p from Appointment a join fetch a.patient p where APPOINTMENT_TYPE = 'AppointmentPharmacist'")
	public List<Patient> findAllPharmacist();
}
