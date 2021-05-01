package com.MRSISA2021_T15.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.model.Complaint;
import com.MRSISA2021_T15.model.ComplaintDermatologist;
import com.MRSISA2021_T15.model.ComplaintPharmacist;
import com.MRSISA2021_T15.model.ComplaintPharmacy;
import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.model.Pharmacy;
import com.MRSISA2021_T15.model.SystemAdmin;
import com.MRSISA2021_T15.repository.ComplaintRepository;
import com.MRSISA2021_T15.repository.DermatologistRepository;
import com.MRSISA2021_T15.repository.PharmacistRepository;
import com.MRSISA2021_T15.repository.PharmacySearchRepository;

@Service
public class ComplaintService {
	
	@Autowired
	private DermatologistRepository dermatologistRepository;
	
	@Autowired
	private PharmacistRepository pharmacistRepository;
	
	@Autowired
	private PharmacySearchRepository pharmacyRepository;
	
	@Autowired
	private ComplaintRepository complaintRepository;
	
	@Autowired
	private EmailSenderService emailSenderService;
	
	@Autowired
	private Environment env;
	
	public List<Dermatologist>findAllDermatologist(){
		return dermatologistRepository.getAllDermatologist();
	}
	
	public List<Pharmacist>findAllPharmacist(){
		return pharmacistRepository.getAllPharmacist();
	}
	
	public List<Pharmacy>findAllPharmacy(){
		return pharmacyRepository.findAll();
	}
	
	public void addDerComplaint(ComplaintDermatologist com) {
		complaintRepository.save(com);
		
	}
	
	public void addPhaComplaint(ComplaintPharmacist com) {
		complaintRepository.save(com);
	}
	
	public void addPharyComplaint(ComplaintPharmacy com) {
		complaintRepository.save(com);
	}
	
	
	public List<Complaint> findAll(){
		return (List<Complaint>) complaintRepository.findAll();
	}
	
	public List<Complaint> getComplaintsToRespond() {
		return complaintRepository.getComplaintsToRespond();
	}
	
	public List<Complaint> getResponses() {
		return complaintRepository.findBySystemAdmin(((SystemAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
	}
	
	public void sendResponse(Complaint response) {
		Complaint complaint = complaintRepository.findById(response.getId()).get();
		complaint.setResponse(response.getResponse());
		complaint.setSystemAdmin(((SystemAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		complaintRepository.save(complaint);
		SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(complaint.getPatient().getEmail());
        mailMessage.setSubject("Response to complaint number " + complaint.getId());
        mailMessage.setFrom(env.getProperty("spring.mail.username"));
        mailMessage.setText("Your complaint: " + complaint.getText() + "\n\nResponse: " + complaint.getResponse() + "\n\nBest regards,\n" + complaint.getSystemAdmin().getName());
        emailSenderService.sendEmail(mailMessage);
	}
}
