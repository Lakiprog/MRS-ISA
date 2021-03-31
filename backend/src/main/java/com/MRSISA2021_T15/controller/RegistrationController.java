package com.MRSISA2021_T15.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.SystemAdmin;
import com.MRSISA2021_T15.service.RegistrationService;


@RestController
@RequestMapping("/registration")
public class RegistrationController {
	
	@Autowired
	private RegistrationService registrationService;
	
	@PostMapping(value = "/registerPatient", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerPatient(@RequestBody Patient patient) {
		String message = registrationService.registerPatient(patient);
		Gson gson = new GsonBuilder().create();
		if (message == "") {
			return new ResponseEntity<String>(gson.toJson("Registration successfull."), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/registerSystemAdministrator", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerSystemAdministrator(@RequestBody SystemAdmin systemAdmin) {
		String message = registrationService.registerSystemAdmin(systemAdmin);
		Gson gson = new GsonBuilder().create();
		if (message == "") {
			return new ResponseEntity<String>(gson.toJson("Registration successfull."), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/registerDermatologist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerDermatologist(@RequestBody Dermatologist dermatologist) {
		String message = registrationService.registerDermatologist(dermatologist);
		Gson gson = new GsonBuilder().create();
		if (message == "") {
			return new ResponseEntity<String>(gson.toJson("Registration successfull."), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
