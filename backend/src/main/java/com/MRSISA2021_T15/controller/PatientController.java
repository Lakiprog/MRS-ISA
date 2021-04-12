
package com.MRSISA2021_T15.controller;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.service.PatientService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/patients")
public class PatientController {
	@Autowired
	private PatientService service;
	
	
	@GetMapping(value = "/searchPatient/{patientUsername}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Patient searchPatients(@PathVariable("patientUsername") String patientUsername){
		ArrayList<Patient> patients = new ArrayList<Patient>();
		for(Patient p : service.findAllPatients()) {
			if(p.getUsername().compareTo(patientUsername)==0) {
				return p;
			}
		}
		return null; //ovo se ne bi smjelo desiti
	}
	
	
	@GetMapping(value = "/changeDataShow/{patientUsername}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Patient changeDataShow(@PathVariable("patientUsername") String patientUsername){
		for(Patient p : service.findAllPatients()) {
			if(p.getUsername().compareTo(patientUsername)==0) {
				return p;
			}
		}
		return null; //ovo se ne bi smjelo desiti
	}
	
	
	
	@PutMapping(value = "/changeData/{patientUsername}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> changeData(@PathVariable("patientUsername") String patientUsername, @RequestBody Patient patient){
		Patient patientToSend = null;
		String message = null;
		
		boolean flag;
		if(patientUsername != patient.getUsername()) {
			flag = true;
		}else {
			flag = false; // znaci da se promijenio username, ostalo je da provjerimo da li novi username postoji
		}
		
		
		for(Patient p : service.findAllPatients()) {
			if(p.getUsername().compareTo(patient.getUsername()) == 0 && flag == false) { //znaci novi username je vec postojan a nije stari
				message = "The new username is not available";
			}
			if(p.getUsername().compareTo(patientUsername)==0) {
				p.setUsername(patient.getUsername());
				p.setPassword(patient.getPassword());
				patientToSend = p;
				
			}
		}
		
		
		if(patientToSend == null ) {
			message = "The new username is not available";
		}else{
			message = service.updateData(patientToSend);
		}
		
		Gson gson = new GsonBuilder().create();
		if (message == "") {
			return new ResponseEntity<String>(gson.toJson("Update successfull."), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	

}

