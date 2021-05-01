package com.MRSISA2021_T15.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.model.Pharmacy;
import com.MRSISA2021_T15.repository.PharmacyRepository;

@Service
public class PharmacyServiceImpl implements PharmacyService {
	
	@Autowired
	private PharmacyRepository pharmacyRepository;

	@Override
	public void registerPharmacy(Pharmacy pharmacy) {
		pharmacyRepository.save(pharmacy);
	}

	@Override
	public List<Pharmacy> getPharmacies() {
		return (List<Pharmacy>) pharmacyRepository.findAll();
	}

}
