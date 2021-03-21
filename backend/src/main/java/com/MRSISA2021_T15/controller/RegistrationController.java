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
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.service.RegistrationService;


@RestController
@RequestMapping("/registration")
public class RegistrationController {
	
	@Autowired
	private RegistrationService registrationService;
	
	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerPatient(@RequestBody Patient patient) {
		Patient registeredPatient = registrationService.register(patient);
		Gson gson = new GsonBuilder().create();
		if (registeredPatient != null) {
			return new ResponseEntity<String>(gson.toJson("Registration successfull."), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(gson.toJson("The patient already exists!"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
