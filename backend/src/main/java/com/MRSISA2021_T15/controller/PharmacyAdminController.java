package com.MRSISA2021_T15.controller;

import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.Medicine;
import com.MRSISA2021_T15.model.PharmacyAdmin;
import com.MRSISA2021_T15.repository.PharmacyAdminRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(path="/{pharmacyAdminId}/findById")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public Optional<PharmacyAdmin> getPharmacyAdminById(@PathVariable Integer pharmacyAdminId){
        return pharmacyAdminRepository.findById(pharmacyAdminId);
    }
    
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
    }
}
