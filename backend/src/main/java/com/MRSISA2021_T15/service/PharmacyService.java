package com.MRSISA2021_T15.service;

import java.util.List;

import com.MRSISA2021_T15.model.Pharmacy;

public interface PharmacyService {
	
	void registerPharmacy(Pharmacy pharmacy);
	
	List<Pharmacy> getPharmacies();

}
