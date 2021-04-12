package com.MRSISA2021_T15.service;

import java.util.HashMap;

import com.MRSISA2021_T15.model.Pharmacy;

public interface PharmacyService {
	
	String registerPharmacy(Pharmacy pharmacy);
	
	HashMap<Integer, String> getPharmacyAdminsWithNoPharmacy();

}
