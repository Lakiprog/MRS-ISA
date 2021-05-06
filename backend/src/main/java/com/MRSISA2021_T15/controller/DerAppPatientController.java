package com.MRSISA2021_T15.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MRSISA2021_T15.model.Appointment;
import com.MRSISA2021_T15.model.AppointmentDermatologist;
import com.MRSISA2021_T15.model.ComplaintDermatologist;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.Pharmacy;
import com.MRSISA2021_T15.service.AppointmentService;
import com.MRSISA2021_T15.service.PharmacySearchService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/make_dermatologist_appointment")
public class DerAppPatientController {
	@Autowired
	PharmacySearchService service;
	
	@Autowired
	private AppointmentService AppService;
	
	
	
	
	@GetMapping(value = "/getAllPharamacies", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public List<Pharmacy>getAll(){
		return service.findAll();
	}
	
	
	
	@GetMapping(value = "/getAllFreeDerApp", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public List<AppointmentDermatologist>getAllFreeDerApp(){
		return AppService.findAllFreeDermatologicalApp();
	}
	
	
	
	@PostMapping(value = "/send", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String> send(@RequestBody AppointmentDermatologist appointment){
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		appointment.setPatient(p);
		AppService.saveDerApp(appointment);
		
		Gson gson = new GsonBuilder().create();
		return new ResponseEntity<String>(gson.toJson("You schedule appointment with dermatologist."), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getPatientDerApp", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public List<AppointmentDermatologist>getPatientDerApp(){
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return AppService.findAllDerAppWithPatientId(p.getId());
	}
	
	
	
	
}
