package com.MRSISA2021_T15.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.repository.RegistrationRepositoryImpl;

@Service
public class RegistrationServiceImpl implements RegistrationService {
	
	@Autowired
	private RegistrationRepositoryImpl registrationRepositoryImpl;

	@Override
	public Patient register(Patient patient) {
		return registrationRepositoryImpl.register(patient);
	}

}
