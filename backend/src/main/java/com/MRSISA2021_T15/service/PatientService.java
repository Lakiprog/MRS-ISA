package com.MRSISA2021_T15.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.User;

@Service
public class PatientService {
	@Autowired
	private UserRepository repository;
	
	public List<Patient> findAllPatients(){
		return repository.findAllPatients();
	}
	
	
	public String updateData(Patient patient) {
		String message = "";
		if(repository.findById(patient.getId()) != null) {
			repository.deleteById(patient.getId());
			repository.save(patient);
			
			return message;
		}else {
			message = "A user with this email doesn't exist!";
			return message;
		}
	}
	
}
