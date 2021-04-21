package com.MRSISA2021_T15.controller;




import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MRSISA2021_T15.model.Complaint;
import com.MRSISA2021_T15.model.Appointment;
import com.MRSISA2021_T15.model.ComplaintDermatologist;
import com.MRSISA2021_T15.model.ComplaintPharmacist;
import com.MRSISA2021_T15.model.ComplaintPharmacy;
import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.model.Pharmacy;
import com.MRSISA2021_T15.service.AppointmentService;
import com.MRSISA2021_T15.service.ComplaintService;
import com.MRSISA2021_T15.service.PatientService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



@RestController
@RequestMapping("/complaint")
public class ComplaintController {
	@Autowired
	ComplaintService service;
	@Autowired
	AppointmentService service2;
	@Autowired
	PatientService service3;
	
	
	@GetMapping(value = "/getAllDermatologists", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	List<Dermatologist> getAllDermatologists(){
		return service.findAllDermatologist();
	}
	
	
	@GetMapping(value = "/getAllPharmacist", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	List<Pharmacist> getAllPharmacist(){
		return service.findAllPharmacist();
	}
	
	
	@GetMapping(value = "/getAllPharmacy", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	List<Pharmacy> getAllPharmacy(){
		return service.findAllPharmacy();
	}
	
	
	/*
	
	@GetMapping(value = "/getdermatologicalComplaint/{patientUsername}", produces = MediaType.APPLICATION_JSON_VALUE)
	List<ComplaintDermatologist>getdermatologicalComplaint(@PathVariable("patientUsername") String patientUsername){
		List<ComplaintDermatologist> complaints =  service.findAllDerC();
		List<ComplaintDermatologist> list = new ArrayList<ComplaintDermatologist>();
		for(ComplaintDermatologist d : complaints) {
			if(d.getPatient().getUsername().equals(patientUsername)) {
				list.add(d);
			}
		}
		return list;
	}
	
	
	@GetMapping(value = "/getpharmacistComplaint/{patientUsername}", produces = MediaType.APPLICATION_JSON_VALUE)
	List<ComplaintPharmacist>getpharmacistComplaint(@PathVariable("patientUsername") String patientUsername){
		List<ComplaintPharmacist> complaints =  service.findAllPhaC();
		List<ComplaintPharmacist> list = new ArrayList<ComplaintPharmacist>();
		for(ComplaintPharmacist d : complaints) {
			if(d.getPatient().getUsername().equals(patientUsername)) {
				list.add(d);
			}
		}
		return list;
	}
	
	@GetMapping(value = "/getpharmacyComplaint/{patientUsername}", produces = MediaType.APPLICATION_JSON_VALUE)
	List<ComplaintPharmacy>getpharmacyComplaint(@PathVariable("patientUsername") String patientUsername){
		List<ComplaintPharmacy> complaints =  service.findAllPhaYC();
		List<ComplaintPharmacy> list = new ArrayList<ComplaintPharmacy>();
		for(ComplaintPharmacy d : complaints) {
			if(d.getPatient().getName().equals(patientUsername)) {
				list.add(d);
			}
		}
		return list;
	}
	*/
	@GetMapping(value = "/getComplaints/{patientUsername}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	List<Complaint>getpharmacyComplaint(@PathVariable("patientUsername") String patientUsername){
		
		List<Complaint> list = new ArrayList<Complaint>();
		List<Complaint> complaints = service.findAll();
		
		for(Complaint c : complaints) {
			if(c.getPatient().getUsername().equals(patientUsername)) {
				list.add(c);
			}
		}
		
		return list;
	}
	
	
	
	
	@PutMapping(value = "/checkDermatologist/{patientUsername}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String>checkDermatologist(@PathVariable("patientUsername") String patientUsername, @RequestBody Dermatologist dermatologist){
		String message = "";
		boolean found = false;
		
		List<Appointment>appos = service2.findAllDermatologist(dermatologist.getId()); 
		if(!appos.isEmpty()) {
			for(Appointment a : appos) {
				if(a.getPatient() == null) {
					continue;
				}else {
					if(a.getPatient().getUsername().equals(patientUsername)) {
						found = true;
						break;
					}
				}
			}
		}
		
		Gson gson = new GsonBuilder().create();
		if(found) {
			return new ResponseEntity<String>(gson.toJson("You can input complaint"), HttpStatus.OK);
		}else {
			message = "Patient didn't have an appointment with this dermatologist.";
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@PutMapping(value = "/checkPharmacist/{patientUsername}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String>checkPharmacist(@PathVariable("patientUsername") String patientUsername, @RequestBody Pharmacist pharmacist){
		String message = "";
		boolean found = false;
		
		List<Appointment>appos = service2.findAllPharmacist(pharmacist.getId()); 
		if(!appos.isEmpty()) {
			for(Appointment a : appos) {
				if(a.getPatient() == null) {
					continue;
				}else {
					if(a.getPatient().getUsername().equals(patientUsername)) {
						found = true;
						break;
					}
				}
			}
		}
		
		Gson gson = new GsonBuilder().create();
		if(found) {
			return new ResponseEntity<String>(gson.toJson("You can input complaint"), HttpStatus.OK);
		}else {
			message = "Patient didn't have an appointment with this pharmacist.";
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	
	@PutMapping(value = "/checkPharmacy/{patientUsername}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String>checkPharmacy(@PathVariable("patientUsername") String patientUsername, @RequestBody Pharmacy pharmacy){
		String message = "";
		boolean found = false;
		
		List<Appointment>appos = service2.findAllAppointments();
		if(!appos.isEmpty()) {
			for(Appointment a : appos) {
				if(a.getPatient().getUsername().equals(patientUsername) && a.getPharmacy().getId()==pharmacy.getId()) {
					System.out.print(a.getPatient().getId());
					System.out.print(pharmacy.getId());
					found = true;
					break;
				}
			}
		}
		
		Gson gson = new GsonBuilder().create();
		if(found) {
			return new ResponseEntity<String>(gson.toJson("You can input complaint"), HttpStatus.OK);
		}else {
			message = "Patient didn't have an appointment in this pharmacyt.";
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	
	@PostMapping(value = "/addComplaintToDermatologist/{patientUsername}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String> addComplaintToDermatologist(@PathVariable("patientUsername") String patientUsername, @RequestBody ComplaintDermatologist complaint){
		System.out.print(complaint.getText());
		
		String message = "";
		
		List<Patient>patients = service3.findAllPatients();
		for(Patient p : patients) {
			if(p.getUsername().equals(patientUsername)) {
				complaint.setPatient(p);
			}
		}
		
		
		boolean found = false;
		List<Dermatologist> dermatologists = service3.findAllDermatologist();
		for(Dermatologist d : dermatologists) {
			if(d.getId() == complaint.getDermatologist().getId()) {
				complaint.setDermatologist(d);
				service.addDerComplaint(complaint);
				found = true;
			}
		}
		
		if(found == false) {
			message = "Something went wrong";
		}
		
		Gson gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<String>(gson.toJson("Your complaint is send. Thank you for your words."), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	
	//@PostMapping(value = "/addComplaintToPharmacist/{patientUsername}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(value = "/addComplaintToPharmacist/{patientUsername}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String> addComplaintToPharmacist(@PathVariable("patientUsername") String patientUsername, @RequestBody ComplaintPharmacist complaint){
		
		String message = "";
		
		List<Patient>patients = service3.findAllPatients();
		for(Patient p : patients) {
			if(p.getUsername().equals(patientUsername)) {
				complaint.setPatient(p);
			}
		}
		
		boolean found = false;
		List<Pharmacist> pharmacists = service3.findAllPharmacist();
		for(Pharmacist p : pharmacists) {
			if(p.getId() == complaint.getPharmacist().getId()) {
				complaint.setPharmacist(p);
				service.addPhaComplaint(complaint);
				found = true;
			}
		}
		
		if(found == false) {
			message = "Something went wrong";
		}
		
		Gson gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<String>(gson.toJson("Your complaint is send. Thank you for your words."), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	
	
	
	
	@PostMapping(value = "/addComplaintToPharmacy/{patientUsername}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String> addComplaintToPharmacy(@PathVariable("patientUsername") String patientUsername, @RequestBody ComplaintPharmacy complaint){
		String message = "";
		
		List<Patient>patients = service3.findAllPatients();
		for(Patient p : patients) {
			if(p.getUsername().equals(patientUsername)) {
				complaint.setPatient(p);
			}
		}
		
		boolean found = false;
		List<Pharmacy> pharmacies = service.findAllPharmacy();
		for(Pharmacy p : pharmacies) {
			if(p.getId() == complaint.getPharmacy().getId()) {
				complaint.setPharmacy(p);
				service.addPharyComplaint(complaint);
				found = true;
			}
		}
		
		if(found == false) {
			message = "Something went wrong";
		}
		
		Gson gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<String>(gson.toJson("Your complaint is send. Thank you for your words."), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
