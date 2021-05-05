package com.MRSISA2021_T15.service;

import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.model.Absence;
import com.MRSISA2021_T15.model.Appointment;
import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.Employment;
import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.model.PharmacyAdmin;
import com.MRSISA2021_T15.model.SystemAdmin;
import com.MRSISA2021_T15.repository.AbsenceRepository;
import com.MRSISA2021_T15.repository.AppointmentAbsenceRepository;
import com.MRSISA2021_T15.repository.EmploymentRepository;
import com.MRSISA2021_T15.repository.UserRepository;

@Service
public class AbsenceService {
	@Autowired
	private EmploymentRepository emp;
	@Autowired
	private AbsenceRepository abs;
	@Autowired
	private AppointmentAbsenceRepository appoRepo;
	@Autowired
	private UserRepository us;
	@Autowired
	EmailSenderService emails;
	@Autowired
	Environment environment;

	public String createAbsencePharmacist(Absence absence) {
		Pharmacist p = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Appointment> appointments = appoRepo.findAllPharmacistId(p.getId());

		for (Appointment appointment : appointments) {
			if (absence.getStart().isBefore(appointment.getStart()) && absence.getEnd().isAfter(appointment.getEnd())) {
				return "You have an appointment with a patient from " + appointment.getStart() + " to "
						+ appointment.getEnd() + ". ";
			} else if (absence.getStart().isAfter(appointment.getStart())
					&& absence.getStart().isBefore(appointment.getEnd())) {
				return "You have an appointment with a patient from " + appointment.getStart() + " to "
						+ appointment.getEnd() + ". ";
			} else if (absence.getEnd().isAfter(appointment.getStart())
					&& absence.getEnd().isBefore(appointment.getEnd())) {
				return "You have an appointment with a patient from " + appointment.getStart() + " to "
						+ appointment.getEnd() + ". ";
			}
		}
		
		absence.setDoctor(p);
		absence.setApproved(false);
		abs.save(absence);
		
		Employment employment = emp.findByPharmacistId(p.getId());
		for (PharmacyAdmin pharmacyAdmin : us.findAllPharmacyAdmins()) {
			if(pharmacyAdmin.getPharmacy().getId() == employment.getPharmacy().getId()) {
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(pharmacyAdmin.getEmail());
				mailMessage.setSubject("New absence request");
				mailMessage.setFrom(environment.getProperty("spring.mail.username"));
				mailMessage.setText("New absence request from pharmacist " + p.getName() + " " + p.getSurname() 
				+ ". The pharmacist wishes to be absent from " + absence.getStart() + " to " +  absence.getEnd() + ". Please approve it or reject it.");
				emails.sendEmail(mailMessage);
			}
		}

		return "";
	}
	
	public String createAbsenceDermatologist(Absence absence) {
		Dermatologist p = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Appointment> appointments = appoRepo.findAllDermatologistId(p.getId());

		for (Appointment appointment : appointments) {
			if(appointment.getPatient() != null) {
				if (absence.getStart().isBefore(appointment.getStart()) && absence.getEnd().isAfter(appointment.getEnd())) {
					return "You have an appointment with a patient from " + appointment.getStart() + " to "
							+ appointment.getEnd() + ". ";
				} else if (absence.getStart().isAfter(appointment.getStart())
						&& absence.getStart().isBefore(appointment.getEnd())) {
					return "You have an appointment with a patient from " + appointment.getStart() + " to "
							+ appointment.getEnd() + ". ";
				} else if (absence.getEnd().isAfter(appointment.getStart())
						&& absence.getEnd().isBefore(appointment.getEnd())) {
					return "You have an appointment with a patient from " + appointment.getStart() + " to "
							+ appointment.getEnd() + ". ";
				}
			}
		}
		
		absence.setDoctor(p);
		absence.setApproved(false);
		abs.save(absence);
		
		for (SystemAdmin systemAdmin : us.findAllSystemAdmins()) {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(systemAdmin.getEmail());
			mailMessage.setSubject("New absence request");
			mailMessage.setFrom(environment.getProperty("spring.mail.username"));
			mailMessage.setText("New absence request from dermatologist " + p.getName() + " " + p.getSurname() 
			+ ". The dermatologist wishes to be absent from " + absence.getStart() + " to " +  absence.getEnd() + ". Please approve it or reject it.");
			emails.sendEmail(mailMessage);
		}

		return "";
	}
}
