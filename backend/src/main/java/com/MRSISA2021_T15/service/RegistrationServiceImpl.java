package com.MRSISA2021_T15.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.SystemAdmin;
import com.MRSISA2021_T15.repository.RegistrationRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {
	
	@Autowired
	private RegistrationRepository registrationRepository;

	public Patient registerPatient(Patient patient) {
		Patient p = (Patient) registrationRepository.findByEmail(patient.getEmail());
		if (p != null) {
			return null;
		} else {
			return registrationRepository.save(patient);
		}
	}

	public SystemAdmin registerSystemAdmin(SystemAdmin systemAdmin) {
		SystemAdmin sa = (SystemAdmin) registrationRepository.findByEmail(systemAdmin.getEmail());
		if (sa != null) {
			return null;
		} else {
			return registrationRepository.save(systemAdmin);
		}
	}
}