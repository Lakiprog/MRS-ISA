package com.MRSISA2021_T15.controller;


import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.repository.DermatologistRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path="/dermatologist")
public class DermatologistController {
    @Autowired
    private DermatologistRepository dermatologistRepository;

    @RequestMapping(path="/add", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity addNewDermatologist (@RequestBody Dermatologist d) {
        dermatologistRepository.save(d);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{dermatologistId}/delete")
    public void deleteDermatologist(@PathVariable Integer dermatologistId) {
        dermatologistRepository.deleteById(dermatologistId);
    }

    @RequestMapping(path="/all")
    public @ResponseBody Iterable<Dermatologist> getAllDermatologists() {
        return dermatologistRepository.findAll();
    }

    @RequestMapping(path="/{dermatologistId}/findById")
    public Optional<Dermatologist> getDermatologistById(@PathVariable Integer dermatologistId){
        return dermatologistRepository.findById(dermatologistId);
    }

    @RequestMapping(path="/{string}/findByString")
    public ArrayList<Dermatologist> getDermatologistByString(@PathVariable String string){
        Iterable<Dermatologist> dermatologistList = dermatologistRepository.findAll();
        ArrayList<Dermatologist> returnList = new ArrayList<>();
        for(Dermatologist dermatologist: dermatologistList){
            if(dermatologist.getName().toLowerCase().contains(string.toLowerCase())||
                    dermatologist.getSurname().toLowerCase().contains(string.toLowerCase())||
                    dermatologist.getUsername().toLowerCase().contains(string.toLowerCase())||
                    dermatologist.getAdress().toLowerCase().contains(string.toLowerCase())||
                    dermatologist.getCity().toLowerCase().contains(string.toLowerCase())||
                    dermatologist.getCountry().toLowerCase().contains(string.toLowerCase())||
                    dermatologist.getEmail().toLowerCase().contains(string.toLowerCase())||
                    dermatologist.getPhoneNumber().contains(string.toLowerCase()))
                returnList.add(dermatologist);
        }
    return returnList;
    }
    
    @PutMapping(value="/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> putDermatologist(@RequestBody Dermatologist d){
    	Gson gson = new GsonBuilder().create();
		if (dermatologistRepository.findByEmail(d.getEmail()) != null && 
				!dermatologistRepository.findByEmail(d.getEmail()).getUsername().equals(d.getUsername())) {
			return new ResponseEntity<String>(gson.toJson("A user with this email already exists!"), HttpStatus.INTERNAL_SERVER_ERROR);
		} else if (dermatologistRepository.findByUsername(d.getUsername()) == null) {
			return new ResponseEntity<String>(gson.toJson("User: " + d.getUsername() + " does not exist!"), HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			Dermatologist updatedDermatologist = (Dermatologist) dermatologistRepository.findByUsername(d.getUsername());
			updatedDermatologist.setEmail(d.getEmail());
			updatedDermatologist.setPassword(d.getPassword());
			updatedDermatologist.setName(d.getName());
			updatedDermatologist.setSurname(d.getSurname());
			updatedDermatologist.setAdress(d.getAdress());
			updatedDermatologist.setCity(d.getCity());
			updatedDermatologist.setCountry(d.getCountry());
			updatedDermatologist.setPhoneNumber(d.getPhoneNumber());
			updatedDermatologist.setRating(d.getRating());
			dermatologistRepository.save(updatedDermatologist);
			return new ResponseEntity<String>(gson.toJson("Update Succesfull!"), HttpStatus.OK);
		}
	}

}
