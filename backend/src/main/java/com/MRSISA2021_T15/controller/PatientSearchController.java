package com.MRSISA2021_T15.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.service.PatientSearchService;

@RestController
@RequestMapping("/patient-search")
public class PatientSearchController {
	@Autowired
	private PatientSearchService service;
	
	@PostMapping(value = "/searchPatientsPharmacist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Patient> searchPatients(@RequestBody String start){
		if(start == "") {
			return service.patientsPharmacist();
		}else {
			return service.patientsPharmacist(start);
		}
	}
}
