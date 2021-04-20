package com.MRSISA2021_T15.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.model.Complaint;
import com.MRSISA2021_T15.model.ComplaintDermatologist;
import com.MRSISA2021_T15.model.ComplaintPharmacist;
import com.MRSISA2021_T15.model.ComplaintPharmacy;
import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.model.Pharmacy;

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
	
	
}
