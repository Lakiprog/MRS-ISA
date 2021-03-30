package com.MRSISA2021_T15.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.model.Appointment;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.repository.PatientSearchRepository;

@Service
public class PatientSearchService {
	
	@Autowired
	private PatientSearchRepository repository;

	public List<Patient> patientsPharmacist(String startsWith){
		return repository.findAllByUsernamePharmacist(startsWith);
	}
	
	public List<Patient> patientsPharmacist(){
		return repository.findAllPharmacist();
	}
	
	public List<Patient> findAll(){
		return repository.findPatients();
	}
	
	public List<Appointment> all(){
		return repository.findAll();
	}
	
	public List<Patient> allPharmacist(Integer id){
		return repository.findPatientsPharmacist(id);
	}
}
