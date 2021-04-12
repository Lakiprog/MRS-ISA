package com.MRSISA2021_T15.controller;


import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.repository.DermatologistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
