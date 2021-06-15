package com.MRSISA2021_T15.controller;

import com.MRSISA2021_T15.model.*;
import com.MRSISA2021_T15.repository.*;
import com.MRSISA2021_T15.service.AbsenceService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/absence")
public class AbsenceController {

	@Autowired
	private AbsenceService service;
	@Autowired
	private AbsenceRepository absenceRepository;
	@Autowired
	private EmploymentRepository employmentRepository;
	@Autowired
	private EmploymentPharmacistsRepository employmentPharmacistRepository;
	@Autowired
	private EmploymentDermatologistRepository employmentDermatologistRepository;
	@Autowired
	private PharmacyRepository pharmacyRepository;
	
	@PostMapping(path="/pharmacist",  consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	public @ResponseBody ResponseEntity<String> createAbsencePharmacist(@RequestBody Absence absence) throws InterruptedException {
		String message = service.createAbsencePharmacist(absence);
		Gson gson = new GsonBuilder().create();
		if (message == "") {
			return new ResponseEntity<String>(gson.toJson("Absence succesfully created. An admin will review your request."), HttpStatus.OK);
		}
		return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping(path="/dermatologist",  consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	public @ResponseBody ResponseEntity<String> createAbsenceDermatologist(@RequestBody Absence absence) throws InterruptedException {
		String message = service.createAbsenceDermatologist(absence);
		Gson gson = new GsonBuilder().create();
		if (message == "") {
			return new ResponseEntity<String>(gson.toJson("Absence succesfully created. An admin will review your request."), HttpStatus.OK);
		}
		return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(path="/getAbsenceRequests/{pharmacyId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
	public @ResponseBody
	List<Absence> getAbsenceRequests(@PathVariable Integer pharmacyId) {
		List<Absence> absenceListAll = absenceRepository.findAll();
		Optional<Pharmacy> pharmacy = pharmacyRepository.findById(pharmacyId);
		//uzimam liste zaposlenih iz moje apoteke
		List<EmploymentPharmacist> pharmacistEmployees = employmentPharmacistRepository.findAllByPharmacy(pharmacy);
		List<EmploymentDermatologist> dermatologistEmployees = employmentDermatologistRepository.findAllByPharmacy(pharmacy);
		//stavljam id-eve zaposlenih u listu
		List employeeIds = new ArrayList();
		for ( EmploymentPharmacist e : pharmacistEmployees) {
			employeeIds.add(e.getPharmacist().getId());
		}
		for ( EmploymentDermatologist e : dermatologistEmployees) {
			employeeIds.add(e.getDermatologist().getId());
		}
		List returnList = new ArrayList<Absence>();
		for ( Absence a : absenceListAll) {
			//pored svih zahteva za odsustvom proveravam i da li zahtev dolazi od osobe zaposlene u mojoj apoteci
			if (a.isApproved() == false && employeeIds.contains(a.getDoctor().getId())) {
				returnList.add(a);
			}
		}

		return returnList;
	}


	@PutMapping(path="/approveAbsenceRequest", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
	public @ResponseBody ResponseEntity<String> approveAbsenceRequest(@RequestBody AbsenceDto absenceDto) {

		List<Absence> absenceListAll = absenceRepository.findAll();
		for ( Absence a : absenceListAll) {
			if (a.getId() == absenceDto.getId()){
				a.setApproved(true);
				absenceRepository.save(a);
			}
		}

		return ResponseEntity.ok().build();
	}

	@DeleteMapping(path ="/declineAbsenceRequest", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
	public @ResponseBody ResponseEntity<String> declineAbsenceRequest(@RequestBody AbsenceDto absenceDto) {
		List<Absence> absenceListAll = absenceRepository.findAll();

		service.declineAbsenceRequest(absenceDto.getDoctor(), absenceDto);

		for ( Absence a : absenceListAll) {
			if (a.getId() == absenceDto.getId()){
				absenceRepository.delete(a);
			}
		}

		return ResponseEntity.ok().build();
	}

}
