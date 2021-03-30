package com.MRSISA2021_T15.service;

import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.SystemAdmin;

public interface RegistrationService {
	
	Patient registerPatient(Patient patient);
	
	SystemAdmin registerSystemAdmin(SystemAdmin systemAdmin);

}