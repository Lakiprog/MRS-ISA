package com.MRSISA2021_T15.controller;


import com.MRSISA2021_T15.model.Action;
import com.MRSISA2021_T15.model.PatientSubPharmacy;
import com.MRSISA2021_T15.repository.ActionRepository;
import com.MRSISA2021_T15.repository.PatientSubPharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/action")
public class ActionController {

    @Autowired
    private ActionRepository actionRepository;
    @Autowired
    private PatientSubPharmacyRepository patientSubPharmacyRepository;
    @Autowired
    JavaMailSender emails;
    @Autowired
    Environment environment;


    @PostMapping(value = "/createAction")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public ResponseEntity<String> createAction(@RequestBody Action a) {

        List<PatientSubPharmacy> listOfSubscribedPatients = patientSubPharmacyRepository.findAllByPharmacy(a.getPharmacy());
        for (PatientSubPharmacy sub : listOfSubscribedPatients) {
            actionRepository.save(a);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(sub.getPatient().getEmail());
            mailMessage.setSubject("New action announcement!");
            mailMessage.setFrom(environment.getProperty("spring.mail.username"));
            mailMessage.setText("Hi " + sub.getPatient().getName() + " " + sub.getPatient().getSurname()
                    + ", we at " + a.getPharmacy().getName()
                    + " have a new action to announce!\nThe details are:\n" + a.getDescription()
                    + ".\nAction lasts from "+ a.getStartingDate()+ " to "+ a.getEndingDate());
            emails.send(mailMessage);
        }

        return ResponseEntity.ok().build();
    }
}
