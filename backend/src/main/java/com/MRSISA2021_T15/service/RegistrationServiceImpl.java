package com.MRSISA2021_T15.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.PharmacyAdmin;
import com.MRSISA2021_T15.model.Role;
import com.MRSISA2021_T15.model.Supplier;
import com.MRSISA2021_T15.model.SystemAdmin;
import com.MRSISA2021_T15.repository.RegistrationRepository;
import com.MRSISA2021_T15.repository.RoleRepository;
import com.MRSISA2021_T15.utils.Constants;

@Service
public class RegistrationServiceImpl implements RegistrationService {
	
	@Autowired
	private RegistrationRepository registrationRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public String registerPatient(Patient patient) {
		String message = "";
		patient.setPassword(passwordEncoder.encode(patient.getPassword()));
		patient.setEnabled(true);
		if (registrationRepository.findByEmail(patient.getEmail()) != null) {
			message = "A user with this email already exists!";
		} else if (registrationRepository.findByUsername(patient.getUsername()) != null) {
			message = "A user with this username already exists!";
		} else {
			List<Role> roles = new ArrayList<Role>();
			Role role = roleRepository.findById(Constants.ROLE_PATIENT).get();
			roles.add(role);
			patient.setRoles(roles);
			registrationRepository.save(patient);

		}
		return message;
	}

	@Override
	public String registerSystemAdmin(SystemAdmin systemAdmin) {
		String message = "";
		systemAdmin.setPassword(passwordEncoder.encode(systemAdmin.getPassword()));
		systemAdmin.setEnabled(true);
		if (registrationRepository.findByEmail(systemAdmin.getEmail()) != null) {
			message = "A user with this email already exists!";
		} else if (registrationRepository.findByUsername(systemAdmin.getUsername()) != null) {
			message = "A user with this username already exists!";
		} else {
			List<Role> roles = new ArrayList<Role>();
			Role role = roleRepository.findById(Constants.ROLE_SYSTEM_ADMIN).get();
			roles.add(role);
			systemAdmin.setRoles(roles);
			registrationRepository.save(systemAdmin);
		}
		return message;
	}

	@Override
	public String registerDermatologist(Dermatologist dermatologist) {
		String message = "";
		dermatologist.setPassword(passwordEncoder.encode(dermatologist.getPassword()));
		dermatologist.setEnabled(true);
		if (registrationRepository.findByEmail(dermatologist.getEmail()) != null) {
			message = "A user with this email already exists!";
		} else if (registrationRepository.findByUsername(dermatologist.getUsername()) != null) {
			message = "A user with this username already exists!";
		} else {
			List<Role> roles = new ArrayList<Role>();
			Role role = roleRepository.findById(Constants.ROLE_DERMATOLOGIST).get();
			roles.add(role);
			dermatologist.setRoles(roles);
			registrationRepository.save(dermatologist);
		}
		return message;
	}

	@Override
	public String registerSupplier(Supplier supplier) {
		String message = "";
		supplier.setPassword(passwordEncoder.encode(supplier.getPassword()));
		supplier.setEnabled(true);
		if (registrationRepository.findByEmail(supplier.getEmail()) != null) {
			message = "A user with this email already exists!";
		} else if (registrationRepository.findByUsername(supplier.getUsername()) != null) {
			message = "A user with this username already exists!";
		} else {
			List<Role> roles = new ArrayList<Role>();
			Role role = roleRepository.findById(Constants.ROLE_SUPPLIER).get();
			roles.add(role);
			supplier.setRoles(roles);
			registrationRepository.save(supplier);
		}
		return message;
	}

	@Override
	public String registerPharmacyAdministrator(PharmacyAdmin pharmacyAdmin) {
		String message = "";
		pharmacyAdmin.setPassword(passwordEncoder.encode(pharmacyAdmin.getPassword()));
		pharmacyAdmin.setEnabled(true);
		if (registrationRepository.findByEmail(pharmacyAdmin.getEmail()) != null) {
			message = "A user with this email already exists!";
		} else if (registrationRepository.findByUsername(pharmacyAdmin.getUsername()) != null) {
			message = "A user with this username already exists!";
		} else {
			List<Role> roles = new ArrayList<Role>();
			Role role = roleRepository.findById(Constants.ROLE_PHARMACY_ADMIN).get();
			roles.add(role);
			pharmacyAdmin.setRoles(roles);
			registrationRepository.save(pharmacyAdmin);
		}
		return message;
	}
}