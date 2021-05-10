package com.MRSISA2021_T15.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MRSISA2021_T15.dto.AppointmentPatient;
import com.MRSISA2021_T15.model.Appointment;
import com.MRSISA2021_T15.model.AppointmentDermatologist;
import com.MRSISA2021_T15.model.AppointmentPharmacist;
import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.service.PatientSearchService;

@RestController
@RequestMapping("/patient-search")
public class PatientSearchController {
	@Autowired
	private PatientSearchService service;

	@GetMapping(value = "/searchPatientsPharmacist/start={start}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	public Collection<AppointmentPatient> searchPatientsPharmacist(@PathVariable("start") String start) {
		Pharmacist p = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ArrayList<AppointmentPatient> patients = new ArrayList<AppointmentPatient>();
		for (Patient patient : service.findAll()) {
			for (Appointment appointment : service.all()) {
				if (appointment instanceof AppointmentPharmacist) {
					AppointmentPharmacist ap = (AppointmentPharmacist) appointment;
					if (ap.getPatient().getId() == patient.getId() && ap.getPharmacist().getId() == p.getId() && ap.getPatient().getUsername().startsWith(start)) {
						AppointmentPatient apa = new AppointmentPatient();
						apa.setPatient(patient);
						apa.setDate(ap.getStart());
						patients.add(apa);
						break;
					}
				}
			}
		}

		return patients;
	}

	@GetMapping(value = "/searchPatientsDermatologist/start={start}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	public Collection<AppointmentPatient> searchPatientsDermatolog(@PathVariable("start") String start) {
		Dermatologist d = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ArrayList<AppointmentPatient> patients = new ArrayList<AppointmentPatient>();
		for (Patient patient : service.findAll()) {
			for (Appointment appointment : service.all()) {
				if (appointment instanceof AppointmentDermatologist) {
					AppointmentDermatologist ap = (AppointmentDermatologist) appointment;
					if (ap.getPatient() != null) {
						if (ap.getPatient().getId() == patient.getId() && ap.getDermatologist().getId() == d.getId()  && ap.getPatient().getUsername().startsWith(start)) {
							AppointmentPatient apa = new AppointmentPatient();
							apa.setPatient(patient);
							apa.setDate(ap.getStart());
							patients.add(apa);
							break;
						}
					}
				}
			}
		}

		return patients;
	}

}
