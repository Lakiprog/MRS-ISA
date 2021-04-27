package com.MRSISA2021_T15.controller;

import com.MRSISA2021_T15.dto.ChangePassword;
import com.MRSISA2021_T15.model.PharmacyAdmin;
import com.MRSISA2021_T15.repository.PharmacyAdminRepository;
import com.MRSISA2021_T15.service.PharmacyAdminService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path="/pharmacyAdmin")
public class PharmacyAdminController {

    @Autowired
    private PharmacyAdminRepository pharmacyAdminRepository;
    @Autowired
    private PharmacyAdminService pharmacyAdminService;

    @RequestMapping(path="/{pharmacyAdminId}/findById")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public Optional<PharmacyAdmin> getPharmacyAdminById(@PathVariable Integer pharmacyAdminId){
        return pharmacyAdminRepository.findById(pharmacyAdminId);
    }
/*
    @PutMapping(path="/{pharmacyAdminId}/update")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public ResponseEntity edit(@PathVariable Integer pharmacyAdminId, @RequestBody PharmacyAdmin pa) throws NotFoundException {
        PharmacyAdmin pharmacyAdmin = pharmacyAdminRepository.findById(pharmacyAdminId).orElseThrow(() -> new NotFoundException("Ne postoji id"));
        pharmacyAdmin.setName(pa.getName());
        pharmacyAdmin.setSurname(pa.getSurname());
        pharmacyAdmin.setUsername(pa.getUsername());
        pharmacyAdmin.setAdress(pa.getAdress());
        pharmacyAdmin.setCity(pa.getCity());
        pharmacyAdmin.setCountry(pa.getCountry());
        pharmacyAdmin.setEmail(pa.getEmail());
        pharmacyAdmin.setPhoneNumber(pa.getPhoneNumber());
        pharmacyAdmin.setPassword(pa.getPassword());
        pharmacyAdminRepository.save(pharmacyAdmin);
        return ResponseEntity.ok().build();
    }*/
    @PutMapping(value = "/updatePharmacyAdminData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public ResponseEntity<String> updatePharmacyAdminData(@RequestBody PharmacyAdmin pharmacyAdmin) {
        String message = pharmacyAdminService.updatePharmacyAdminData(pharmacyAdmin);
        Gson gson = new GsonBuilder().create();
        if (message.equals("")) {
            return new ResponseEntity<String>(gson.toJson("Update successfull."), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updatePassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public ResponseEntity<String> updatePassword(@RequestBody ChangePassword passwords) {
        String message = pharmacyAdminService.updatePassword(passwords);
        Gson gson = new GsonBuilder().create();
        if (message.equals("")) {
            return new ResponseEntity<String>(gson.toJson(""), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/getPharmacyAdminData", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public PharmacyAdmin getPharmacyAdminData() {
        return pharmacyAdminService.getPharmacyAdminData();
    }

}
