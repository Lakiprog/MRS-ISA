package com.MRSISA2021_T15.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;
import com.MRSISA2021_T15.model.Patient;

@Repository
public class RegistrationRepositoryImpl implements RegistrationRepository {
	
	private ArrayList<Patient> patients = new ArrayList<Patient>();
	
	@Override
	public Patient register(Patient patient) {
		for (Patient p : patients) {
			if (p.getEmail().equals(patient.getEmail())) {
				return null;
			}
		}
		patients.add(patient);
		return patient;
	}
}
