package com.MRSISA2021_T15.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.MRSISA2021_T15.model.AppointmentDermatologist;
import com.MRSISA2021_T15.model.AppointmentPharmacist;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.service.AppointmentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/appointment_creation")
public class AppointmentController {

	@Autowired
	private AppointmentService service;
	
	@PostMapping(path="/pharmacist",  consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> makeAppointmentPharmacist(@RequestBody AppointmentPharmacist appointment) {
		String message = service.makeAppointmentPharmacist(appointment);
		Gson gson = new GsonBuilder().create();
		if (message == "") {
			return new ResponseEntity<String>(gson.toJson("Appointment succesfully created."), HttpStatus.OK);
		}
		return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping(path="/dermatologist",  consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> makeDermatologist(@RequestBody AppointmentDermatologist appointment) {
		String message = service.makeAppointmentDermatologist(appointment);
		Gson gson = new GsonBuilder().create();
		if (message == "") {
			return new ResponseEntity<String>(gson.toJson("Appointment succesfully created."), HttpStatus.OK);
		}
		return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping(path="/dermatologistPredefined/{appointmentId}",  consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> makeDermatologistPredefined(@PathVariable("appointmentId") Integer id, @RequestBody Patient patient) {
		String message = service.makeAppointmentDermatologistPredefined(id, patient);
		Gson gson = new GsonBuilder().create();
		if (message == "") {
			return new ResponseEntity<String>(gson.toJson("Appointment succesfully assigned to patient."), HttpStatus.OK);
		}
		return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
