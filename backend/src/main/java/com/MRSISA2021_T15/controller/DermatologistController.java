package com.MRSISA2021_T15.controller;


import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.Medicine;
import com.MRSISA2021_T15.repository.DermatologistRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ArrayList<Optional<Dermatologist>> getDermatologistById(@PathVariable Integer dermatologistId){
        ArrayList<Optional<Dermatologist>> returnList = new ArrayList<>();
        returnList.add(dermatologistRepository.findById(dermatologistId));
        return returnList;
    }

    @RequestMapping(path="/{string}/findByString")
    public ArrayList<Dermatologist> getDermatologistByString(@PathVariable String string){
        Iterable<Dermatologist> dermatologistList = dermatologistRepository.findAll();
        ArrayList<Dermatologist> returnList = new ArrayList<>();
        for(Dermatologist dermatologist: dermatologistList){
            if((dermatologist.getName() != null && dermatologist.getName().toLowerCase().contains(string.toLowerCase()))||
                    (dermatologist.getSurname() != null && dermatologist.getSurname().toLowerCase().contains(string.toLowerCase()))||
                    (dermatologist.getUsername() != null && dermatologist.getUsername().toLowerCase().contains(string.toLowerCase()))||
                    (dermatologist.getAdress() != null && dermatologist.getAdress().toLowerCase().contains(string.toLowerCase()))||
                    (dermatologist.getCity() != null && dermatologist.getCity().toLowerCase().contains(string.toLowerCase()))||
                    (dermatologist.getCountry() != null && dermatologist.getCountry().toLowerCase().contains(string.toLowerCase())||
                    (dermatologist.getEmail() != null && dermatologist.getEmail().toLowerCase().contains(string.toLowerCase()))||
                    (dermatologist.getPhoneNumber() != null && dermatologist.getPhoneNumber().contains(string.toLowerCase()))))
                returnList.add(dermatologist);
        }
    return returnList;
    }

}
