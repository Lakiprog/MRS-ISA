package com.MRSISA2021_T15.controller;

import com.MRSISA2021_T15.model.PatientSubPharmacy;
import com.MRSISA2021_T15.model.Promotion;
import com.MRSISA2021_T15.repository.PatientSubPharmacyRepository;
import com.MRSISA2021_T15.repository.PromotionRepository;
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
@RequestMapping("/promotion")
public class PromotionController {
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private PatientSubPharmacyRepository patientSubPharmacyRepository;
    @Autowired
    JavaMailSender emails;
    @Autowired
    Environment environment;

    @PostMapping(value = "/createPromotion")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public ResponseEntity<String> createPromotion(@RequestBody Promotion p) {

        List<PatientSubPharmacy> listOfSubscribedPatients = patientSubPharmacyRepository.findAllByPharmacy(p.getPharmacy());
        for (PatientSubPharmacy sub : listOfSubscribedPatients) {
            promotionRepository.save(p);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(sub.getPatient().getEmail());
            mailMessage.setSubject("New promotion announcement!");
            mailMessage.setFrom(environment.getProperty("spring.mail.username"));
            mailMessage.setText("Hi " + sub.getPatient().getName() + " " + sub.getPatient().getSurname()
                    + ", we at " + p.getPharmacy().getName()
                    + " have a new promotion to announce!\nThe details are:\n" + p.getDescription()
                    + ".\nPromotion lasts from "+ p.getStartingDate()+ " to "+ p.getEndingDate());
            emails.send(mailMessage);
        }
        return ResponseEntity.ok().build();
    }

}
