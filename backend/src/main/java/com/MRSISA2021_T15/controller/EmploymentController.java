package com.MRSISA2021_T15.controller;

import com.MRSISA2021_T15.model.*;
import com.MRSISA2021_T15.repository.AppointmentRepository;
import com.MRSISA2021_T15.repository.EmploymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employment")
public class EmploymentController {

    @Autowired
    private EmploymentRepository employmentRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping(path="/getPharmacyEmployees/{pharmacyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Employment> getPharmacyEmployees(@PathVariable Integer pharmacyId) {
        List<Employment> employmentList = employmentRepository.findAll();
        List returnList = new ArrayList<Employment>();
        for (Employment e : employmentList) {
            if (e.getPharmacy().getId() == pharmacyId) {
                returnList.add(e);
            }
        }
        return returnList;
    }


    @DeleteMapping(value = "/deleteDermatologistFromPharmacy/{pharmacyId}")
    public ResponseEntity<String> deleteDermatologistFromPharmacy(@RequestBody Dermatologist d, @PathVariable Integer pharmacyId){
        List<EmploymentDermatologist> allEmploymentDermatologist = employmentRepository.findAllByDermatologist(d);
        List<Appointment> allAppointmentDermatologist = appointmentRepository.findAllDermatologistId(d.getId());

        EmploymentDermatologist e = new EmploymentDermatologist();
        for (EmploymentDermatologist ePh: allEmploymentDermatologist) {
            if(ePh.getPharmacy().getId() == pharmacyId){
                e=ePh;
            }
        }
        for (Appointment a : allAppointmentDermatologist
        ) {
            if (a.getEnd().isAfter(LocalDateTime.now())){
                return ResponseEntity.ok().build();
            }
        }
        employmentRepository.deleteById(e.getId());

        return ResponseEntity.ok().build();

    }

    @DeleteMapping(value = "/deletePharmacistFromPharmacy/{pharmacyId}")
    public ResponseEntity<String> deletePharmacistFromPharmacy(@RequestBody Pharmacist p, @PathVariable Integer pharmacyId){
        EmploymentPharmacist e = employmentRepository.findByPharmacistId(p.getId());
        List<Appointment> allAppointmentPharmacist = appointmentRepository.findAllPharmacistId(p.getId());

        for (Appointment a : allAppointmentPharmacist
        ) {
            if (a.getEnd().isAfter(LocalDateTime.now())){
                return ResponseEntity.ok().build();
            }
        }
        employmentRepository.deleteById(e.getId());

        return ResponseEntity.ok().build();

    }

}
