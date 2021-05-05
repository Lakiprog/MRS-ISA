package com.MRSISA2021_T15.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MRSISA2021_T15.model.AppointmentConsultationPoints;
import com.MRSISA2021_T15.model.Category;
import com.MRSISA2021_T15.model.CategoryName;
import com.MRSISA2021_T15.service.LoyaltyProgramService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/loyaltyProgram")
public class LoyaltyProgramController {
	
	@Autowired
	private LoyaltyProgramService loyaltyProgramService;
	
	@PostMapping(value = "/defineCategories", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	public ResponseEntity<String> defineCategories(@RequestBody Category category) {
		String message = loyaltyProgramService.defineCategories(category);
		Gson gson = new GsonBuilder().create();
		return new ResponseEntity<String>(gson.toJson(message), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getCategoryNames", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	public CategoryName[] getCategoryNames() {
		return loyaltyProgramService.getCategoryNames();
	}
	
	@PostMapping(value = "/definePointsForAppointmentAndConsulation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	public ResponseEntity<String> definePointsForAppointmentAndConsulation(@RequestBody AppointmentConsultationPoints acp) {
		String message = loyaltyProgramService.definePointsForAppointmentAndConsulation(acp);
		Gson gson = new GsonBuilder().create();
		return new ResponseEntity<String>(gson.toJson(message), HttpStatus.OK);
	}
}