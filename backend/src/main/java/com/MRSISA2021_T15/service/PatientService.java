package com.MRSISA2021_T15.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.MRSISA2021_T15.repository.PatientSubPharmacyRepository;
import com.MRSISA2021_T15.repository.PromotionRepository;
import com.MRSISA2021_T15.repository.UserRepository;
import com.MRSISA2021_T15.dto.ChangePassword;
import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.PatientSubPharmacy;
import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.model.Pharmacy;

@Service
public class PatientService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private PatientSubPharmacyRepository patientSubPharmacyRepository;
	
	@Autowired
	private PromotionRepository promotionRepository;
	
	@Autowired
    private EmailSenderService emailSenderService;
	
	@Autowired
	private Environment env;
	
	public List<Patient> findAllPatients(){
		return repository.findAllPatients();
	}
	
	public List<Dermatologist> findAllDermatologist(){
		return repository.findAllDermatologist();
	}
	
	public Dermatologist findDermatologistWithId(Integer id) {
		return repository.findDermatologistWithId(id);
	}
	
	public Pharmacist findPharmacistWithId(Integer id) {
		return repository.findPharmacistWithId(id);
	}
	
	
	public List<Pharmacist>findAllPharmacist(){
		return repository.findAllPharmacist();
	}
	
	public String updatePatientData(Patient p) {
		String message = "";
		Patient currentUser = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (currentUser != null) {
			currentUser.setName(p.getName());
			currentUser.setSurname(p.getSurname());
			currentUser.setAddress(p.getAddress());
			currentUser.setCity(p.getCity());
			currentUser.setCountry(p.getCountry());
			currentUser.setPhoneNumber(p.getPhoneNumber());
			repository.save(currentUser);
		} else {
			message = "Update unsuccessfull!";
		}
		return message;
	}
	
	
	public String updatePassword(ChangePassword passwords) {
		String message = "";
		Patient currentUser = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (currentUser != null) {
			if (!passwordEncoder.matches(passwords.getOldPassword(), currentUser.getPassword())) {
				message = "Wrong old password!";
			} else {
				currentUser.setPassword(passwordEncoder.encode(passwords.getPassword()));
				repository.save(currentUser);
			}
		} else {
			message = "Password update unsuccessfull!";
		}
		return message;
	}
	
	
	public Patient getPatientData() {
		return (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	public void subscribeToPharamacy(@RequestBody Pharmacy pharmacy) {
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PatientSubPharmacy patientSubPharmacy = patientSubPharmacyRepository.findByPharamcyByPharmacyIdAndPatientId(pharmacy.getId(), patient.getId());
		if (patientSubPharmacy != null) {
			patientSubPharmacy.setSubscribed(true);
			patientSubPharmacyRepository.save(patientSubPharmacy);
		} else {
			PatientSubPharmacy psp = new PatientSubPharmacy();
			psp.setPatient(patient);
			psp.setPharmacy(pharmacy);
			psp.setSubscribed(true);
			patientSubPharmacyRepository.save(psp);
		}
	}
	
	public void unsubscribeToPharamacy(@RequestBody Pharmacy pharmacy) {
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PatientSubPharmacy patientSubPharmacy = patientSubPharmacyRepository.findByPharamcyByPharmacyIdAndPatientId(pharmacy.getId(), patient.getId());
		if (patientSubPharmacy != null) {
			patientSubPharmacy.setSubscribed(false);
			patientSubPharmacyRepository.save(patientSubPharmacy);
		}
	}
	
	public List<Pharmacy> getSubscribedPharmacies() {
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return patientSubPharmacyRepository.getSubscribedPharmaciesForPatient(patient.getId());
	}
	
	@Scheduled(fixedDelayString = "PT24H")
	public void sendPromotionMails() throws InterruptedException {
		List<PatientSubPharmacy> subscribedPatients = patientSubPharmacyRepository.findBySubscribedTrue();
		for (PatientSubPharmacy psp: subscribedPatients) {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(psp.getPatient().getEmail());
            mailMessage.setSubject("Pharmacy " + psp.getPharmacy().getName() + " PROMOTION!!!!");
            mailMessage.setFrom(env.getProperty("spring.mail.username"));
            mailMessage.setText(promotionRepository.getDescriptionByPharmacyId(psp.getPharmacy().getId()));
            emailSenderService.sendEmail(mailMessage);
            Thread.sleep(3000);
		}
	}
}
