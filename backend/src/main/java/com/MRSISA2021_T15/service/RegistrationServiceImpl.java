package com.MRSISA2021_T15.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.Supplier;
import com.MRSISA2021_T15.model.SystemAdmin;
import com.MRSISA2021_T15.repository.RegistrationRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {
	
	@Autowired
	private RegistrationRepository registrationRepository;

	@Override
	public String registerPatient(Patient patient) {
		String message = "";
		if (registrationRepository.findByEmail(patient.getEmail()) != null) {
			message = "A user with this email already exists!";
		} else if (registrationRepository.findByUsername(patient.getUsername()) != null) {
			message = "A user with this username already exists!";
		} else {
			registrationRepository.save(patient);

		}
		return message;
	}

	@Override
	public String registerSystemAdmin(SystemAdmin systemAdmin) {
		String message = "";
		if (registrationRepository.findByEmail(systemAdmin.getEmail()) != null) {
			message = "A user with this email already exists!";
		} else if (registrationRepository.findByUsername(systemAdmin.getUsername()) != null) {
			message = "A user with this username already exists!";
		} else {
			registrationRepository.save(systemAdmin);
		}
		return message;
	}

	@Override
	public String registerDermatologist(Dermatologist dermatologist) {
		String message = "";
		if (registrationRepository.findByEmail(dermatologist.getEmail()) != null) {
			message = "A user with this email already exists!";
		} else if (registrationRepository.findByUsername(dermatologist.getUsername()) != null) {
			message = "A user with this username already exists!";
		} else {
			registrationRepository.save(dermatologist);
		}
		return message;
	}

	@Override
	public String registerSupplier(Supplier supplier) {
		String message = "";
		if (registrationRepository.findByEmail(supplier.getEmail()) != null) {
			message = "A user with this email already exists!";
		} else if (registrationRepository.findByUsername(supplier.getUsername()) != null) {
			message = "A user with this username already exists!";
		} else {
			registrationRepository.save(supplier);
		}
		return message;
	}
}