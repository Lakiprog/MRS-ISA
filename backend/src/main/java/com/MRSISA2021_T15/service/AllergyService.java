package com.MRSISA2021_T15.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.model.Allergy;
import com.MRSISA2021_T15.repository.AllergyRepository;

@Service
public class AllergyService {

	@Autowired
	AllergyRepository repository;
	
	public List<Allergy> getForPatient(Integer id){
		return repository.findAllPatients(id);
	}
	
}
