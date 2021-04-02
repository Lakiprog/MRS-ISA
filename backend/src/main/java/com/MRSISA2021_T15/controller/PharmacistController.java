package com.MRSISA2021_T15.controller;


import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.repository.PharmacistRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
