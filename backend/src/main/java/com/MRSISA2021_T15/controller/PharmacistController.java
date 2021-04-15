package com.MRSISA2021_T15.controller;


import com.MRSISA2021_T15.model.Appointment;
import com.MRSISA2021_T15.model.Event;
import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.model.Supplier;
import com.MRSISA2021_T15.repository.PharmacistRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path="/pharmacist")
public class PharmacistController {
    @Autowired
    private PharmacistRepository pharmacistRepository;

    @RequestMapping(path="/add", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity addNewPharmacist (@RequestBody Pharmacist p) {
        pharmacistRepository.save(p);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{pharmacistId}/delete")
    public void deletePharmacist(@PathVariable Integer pharmacistId) {
        pharmacistRepository.deleteById(pharmacistId);
    }

    @RequestMapping(path="/all")
    public @ResponseBody Iterable<Pharmacist> getAllPharmacists() {
        return pharmacistRepository.findAll();
    }
    
    @GetMapping(value = "/get/{pharmacistId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Pharmacist> getPharmacist(@PathVariable("pharmacistId") Integer pharmacistId){
		return pharmacistRepository.findById(pharmacistId);
	}
    
    @PutMapping(value="/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> putPharmacist(@RequestBody Pharmacist p){
		Gson gson = new GsonBuilder().create();
		if (pharmacistRepository.findByEmail(p.getEmail()) != null && 
				!pharmacistRepository.findByEmail(p.getEmail()).getUsername().equals(p.getUsername())) {
			return new ResponseEntity<String>(gson.toJson("A user with this email already exists!"), HttpStatus.INTERNAL_SERVER_ERROR);
		} else if (pharmacistRepository.findByUsername(p.getUsername()) == null) {
			return new ResponseEntity<String>(gson.toJson("User: " + p.getUsername() + " does not exist!"), HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			Pharmacist updatedPharmacist = (Pharmacist) pharmacistRepository.findByUsername(p.getUsername());
			updatedPharmacist.setEmail(p.getEmail());
			updatedPharmacist.setPassword(p.getPassword());
			updatedPharmacist.setName(p.getName());
			updatedPharmacist.setSurname(p.getSurname());
			updatedPharmacist.setAdress(p.getAdress());
			updatedPharmacist.setCity(p.getCity());
			updatedPharmacist.setCountry(p.getCountry());
			updatedPharmacist.setPhoneNumber(p.getPhoneNumber());
			updatedPharmacist.setRating(p.getRating());
			pharmacistRepository.save(updatedPharmacist);
			return new ResponseEntity<String>(gson.toJson("Update Succesfull!"), HttpStatus.OK);
		}
	}
}
