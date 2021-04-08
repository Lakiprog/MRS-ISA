package com.MRSISA2021_T15.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MRSISA2021_T15.model.Appointment;
import com.MRSISA2021_T15.model.AppointmentDermatologist;
import com.MRSISA2021_T15.model.AppointmentPharmacist;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.service.PatientSearchService;

@RestController
@RequestMapping("/patient-search")
public class PatientSearchController {
	@Autowired
	private PatientSearchService service;
	
	@GetMapping(value = "/searchPatientsPharmacist/{pharmacistId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Patient> searchPatientsPharmacist(@PathVariable("pharmacistId") Integer pharmacistId){
			ArrayList<Patient> patients = new ArrayList<Patient>();
			for (Patient patient : service.findAll()) {
				for (Appointment appointment : service.all()) {
					if(appointment instanceof AppointmentPharmacist) {
						AppointmentPharmacist ap = (AppointmentPharmacist) appointment;
						if(ap.getPatient().getId() == patient.getId() && ap.getPharmacist().getId() == pharmacistId) {
							patients.add(patient);
							break;
						}
					}
				}
			}
			
			return patients;
	}
	
	@GetMapping(value = "/searchPatientsDermatologist/{dermatologistId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Patient> searchPatientsDermatolog(@PathVariable("dermatologistId") Integer dermatologistId){
			ArrayList<Patient> patients = new ArrayList<Patient>();
			for (Patient patient : service.findAll()) {
				for (Appointment appointment : service.all()) {
					if(appointment instanceof AppointmentDermatologist) {
						AppointmentDermatologist ap = (AppointmentDermatologist) appointment;
						if(ap.getPatient() != null) {
							if(ap.getPatient().getId() == patient.getId() && ap.getDermatologist().getId() == dermatologistId) {
								patients.add(patient);
								break;
							}
						}
					}
				}
			}
			
			return patients;
	}
	
	
	
	
}
