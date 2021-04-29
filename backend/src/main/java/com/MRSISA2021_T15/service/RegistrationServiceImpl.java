package com.MRSISA2021_T15.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.MRSISA2021_T15.dto.ConfirmationToken;
import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.PharmacyAdmin;
import com.MRSISA2021_T15.model.Role;
import com.MRSISA2021_T15.model.Supplier;
import com.MRSISA2021_T15.model.SystemAdmin;
import com.MRSISA2021_T15.repository.ConfirmationTokenRepository;
import com.MRSISA2021_T15.repository.RegistrationRepository;
import com.MRSISA2021_T15.repository.RoleRepository;
import com.MRSISA2021_T15.utils.Constants;

@Service
public class RegistrationServiceImpl implements RegistrationService {
	
	@Autowired
	private RegistrationRepository registrationRepository;
	
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public String registerPatient(Patient patient) {
		String message = "";
		if (registrationRepository.findByEmail(patient.getEmail()) != null) {
			message = "A user with this email already exists!";
		} else if (registrationRepository.findByUsername(patient.getUsername()) != null) {
			message = "A user with this username already exists!";
		} else {
			patient.setPassword(passwordEncoder.encode(patient.getPassword()));
			patient.setEnabled(false);
			List<Role> roles = new ArrayList<Role>();
			Role role = roleRepository.findById(Constants.ROLE_PATIENT).get();
			roles.add(role);
			patient.setRoles(roles);
			ConfirmationToken confirmationToken = new ConfirmationToken(patient);
			try {
				SimpleMailMessage mailMessage = new SimpleMailMessage();
	            mailMessage.setTo(patient.getEmail());
	            mailMessage.setSubject("Verify account");
	            mailMessage.setFrom(env.getProperty("spring.mail.username"));
	            mailMessage.setText("To verify your account, please click here: "
	            		+ "http://localhost:8080/registration/confirmAccount?token=" + confirmationToken.getConfirmationToken());
	            javaMailSender.send(mailMessage);
	            registrationRepository.save(patient);
	            confirmationTokenRepository.save(confirmationToken);
			} catch (Exception e) {
				message = "Server error: registration unsuccessfull!";
			}
		}
		return message;
	}
	
	@Override
	public ModelAndView confirmAccount(ModelAndView modelAndView, String confirmationToken) {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if (token != null) {
            Patient patient = (Patient) registrationRepository.findByEmail(token.getUser().getEmail());
            patient.setEnabled(true);
            registrationRepository.save(patient);
            modelAndView.setViewName("accountVerified");
        } else {
        	modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }
        return modelAndView;
	}

	@Override
	public String registerSystemAdmin(SystemAdmin systemAdmin) {
		String message = "";
		if (registrationRepository.findByEmail(systemAdmin.getEmail()) != null) {
			message = "A user with this email already exists!";
		} else if (registrationRepository.findByUsername(systemAdmin.getUsername()) != null) {
			message = "A user with this username already exists!";
		} else {
			systemAdmin.setPassword(passwordEncoder.encode(systemAdmin.getPassword()));
			systemAdmin.setEnabled(true);
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
		if (registrationRepository.findByEmail(dermatologist.getEmail()) != null) {
			message = "A user with this email already exists!";
		} else if (registrationRepository.findByUsername(dermatologist.getUsername()) != null) {
			message = "A user with this username already exists!";
		} else {
			dermatologist.setPassword(passwordEncoder.encode(dermatologist.getPassword()));
			dermatologist.setEnabled(true);
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
		if (registrationRepository.findByEmail(supplier.getEmail()) != null) {
			message = "A user with this email already exists!";
		} else if (registrationRepository.findByUsername(supplier.getUsername()) != null) {
			message = "A user with this username already exists!";
		} else {
			supplier.setPassword(passwordEncoder.encode(supplier.getPassword()));
			supplier.setEnabled(true);
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
		if (registrationRepository.findByEmail(pharmacyAdmin.getEmail()) != null) {
			message = "A user with this email already exists!";
		} else if (registrationRepository.findByUsername(pharmacyAdmin.getUsername()) != null) {
			message = "A user with this username already exists!";
		} else {
			pharmacyAdmin.setPassword(passwordEncoder.encode(pharmacyAdmin.getPassword()));
			pharmacyAdmin.setEnabled(true);
			List<Role> roles = new ArrayList<Role>();
			Role role = roleRepository.findById(Constants.ROLE_PHARMACY_ADMIN).get();
			roles.add(role);
			pharmacyAdmin.setRoles(roles);
			registrationRepository.save(pharmacyAdmin);
		}
		return message;
	}
}