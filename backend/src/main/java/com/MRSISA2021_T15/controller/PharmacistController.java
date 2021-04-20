package com.MRSISA2021_T15.controller;


import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.repository.PharmacistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

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

    @RequestMapping(path="/{pharmacistId}/findById")
    public ArrayList<Optional<Pharmacist>> getPharmacistById(@PathVariable Integer pharmacistId){
        ArrayList<Optional<Pharmacist>> returnList = new ArrayList<>();
        returnList.add(pharmacistRepository.findById(pharmacistId));
        return returnList;
    }

    @RequestMapping(path="/{string}/findByString")
    public ArrayList<Pharmacist> getPharmacistByString(@PathVariable String string){
        Iterable<Pharmacist> pharmacistList = pharmacistRepository.findAll();
        ArrayList<Pharmacist> returnList = new ArrayList<>();
        for(Pharmacist pharmacist: pharmacistList){
            if((pharmacist.getName() != null && pharmacist.getName().toLowerCase().contains(string.toLowerCase()))||
                    (pharmacist.getSurname() != null && pharmacist.getSurname().toLowerCase().contains(string.toLowerCase()))||
                    (pharmacist.getUsername() != null && pharmacist.getUsername().toLowerCase().contains(string.toLowerCase()))||
                    (pharmacist.getAdress() != null && pharmacist.getAdress().toLowerCase().contains(string.toLowerCase()))||
                    (pharmacist.getCity() != null && pharmacist.getCity().toLowerCase().contains(string.toLowerCase()))||
                    (pharmacist.getCountry() != null && pharmacist.getCountry().toLowerCase().contains(string.toLowerCase())||
                            (pharmacist.getEmail() != null && pharmacist.getEmail().toLowerCase().contains(string.toLowerCase()))||
                            (pharmacist.getPhoneNumber() != null && pharmacist.getPhoneNumber().contains(string.toLowerCase()))))
                returnList.add(pharmacist);
        }
        return returnList;
    }
}
