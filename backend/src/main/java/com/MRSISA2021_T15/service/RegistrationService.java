package com.MRSISA2021_T15.service;

import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.Supplier;
import com.MRSISA2021_T15.model.SystemAdmin;

public interface RegistrationService {
	
	String registerPatient(Patient patient);
	
	String registerSystemAdmin(SystemAdmin systemAdmin);
	
	String registerDermatologist(Dermatologist dermatologist);
	
	String registerSupplier(Supplier supplier);

}