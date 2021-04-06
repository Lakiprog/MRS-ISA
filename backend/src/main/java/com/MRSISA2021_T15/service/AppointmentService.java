package com.MRSISA2021_T15.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.model.Appointment;
import com.MRSISA2021_T15.model.AppointmentDermatologist;
import com.MRSISA2021_T15.model.AppointmentPharmacist;
import com.MRSISA2021_T15.model.EmploymentDermatologist;
import com.MRSISA2021_T15.model.EmploymentPharmacist;
import com.MRSISA2021_T15.repository.AppointmentCreationRepository;
import com.MRSISA2021_T15.repository.EmploymentRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentCreationRepository repository;
	@Autowired
	private EmploymentRepository repo2;
	
	public List<Appointment> findAllPharmacist(Integer id){
		return repository.findAllPharmacistId(id);
	}
	
	public List<Appointment> findAllDermatologist(Integer id){
		return repository.findAllDermatologistId(id);
	}
	
	public List<Appointment> findAllPatients(Integer id){
		return repository.findAllPatientsId(id);
	}
	
	public String makeAppointmentPharmacist(AppointmentPharmacist appointment) {
		List<Appointment> appointmentsPatient = findAllPatients(appointment.getPatient().getId());
		List<Appointment> appointmentsPharmacist = findAllPharmacist(appointment.getPharmacist().getId());
		EmploymentPharmacist employment = repo2.findByPharmacistId(appointment.getPharmacist().getId());
		
		for (Appointment appointment2 : appointmentsPharmacist) {
			if((appointment.getStart().isBefore(appointment2.getStart()) || appointment.getStart().isEqual(appointment2.getStart()))
					&& (appointment2.getStart().isBefore(appointment.getEnd()) || appointment2.getStart().isEqual(appointment.getEnd()))) {
				return "This pharmacist has already an appointment planned at that time!";
			}else if((appointment.getStart().isBefore(appointment2.getEnd()) || appointment.getStart().isEqual(appointment2.getEnd()))
					&& (appointment.getEnd().isAfter(appointment2.getEnd()) || appointment2.getEnd().isEqual(appointment.getEnd()))){
				return "This pharmacist has already an appointment planned at that time!";
			}else if(appointment.getStart().getHour() < employment.getStart() || appointment.getEnd().getHour() > employment.getEnd()) {
				return "This pharmacist doesnt work at that time!";
			}
		}
		
		for (Appointment appointment2 : appointmentsPatient) {
			if((appointment.getStart().isBefore(appointment2.getStart()) || appointment.getStart().isEqual(appointment2.getStart()))
					&& (appointment2.getStart().isBefore(appointment.getEnd()) || appointment2.getStart().isEqual(appointment.getEnd()))) {
				return "This patient has already an appointment planned at that time!";
			}else if((appointment.getStart().isBefore(appointment2.getEnd()) || appointment.getStart().isEqual(appointment2.getEnd()))
					&& (appointment.getEnd().isAfter(appointment2.getEnd()) || appointment2.getEnd().isEqual(appointment.getEnd()))){
				return "This patient has already an appointment planned at that time!";
			}
		}
		
		repository.save(appointment);
		return "";
	}
	
	public String makeAppointmentDermatologist(AppointmentDermatologist appointment) {
		List<Appointment> appointmentsPatient = findAllPatients(appointment.getPatient().getId());
		List<Appointment> appointmentsPharmacist = findAllDermatologist(appointment.getDermatologist().getId());
		EmploymentDermatologist employment = new EmploymentDermatologist();
		for (EmploymentDermatologist e : repo2.findByDermatologistId(appointment.getDermatologist().getId())) {
			if(e.getPharmacy().getId().equals(appointment.getPharmacy().getId())) {
				employment = e;
				break;
			}
		}
		
		for (Appointment appointment2 : appointmentsPharmacist) {
			if((appointment.getStart().isBefore(appointment2.getStart()) || appointment.getStart().isEqual(appointment2.getStart()))
					&& (appointment2.getStart().isBefore(appointment.getEnd()) || appointment2.getStart().isEqual(appointment.getEnd()))) {
				return "This dermatologist has already an appointment planned at that time!";
			}else if((appointment.getStart().isBefore(appointment2.getEnd()) || appointment.getStart().isEqual(appointment2.getEnd()))
					&& (appointment.getEnd().isAfter(appointment2.getEnd()) || appointment2.getEnd().isEqual(appointment.getEnd()))){
				return "This dermatologist has already an appointment planned at that time!";
			}else if(appointment.getStart().getHour() < employment.getStart() || appointment.getEnd().getHour() > employment.getEnd()) {
				return "This dermatologist doesnt work at that time!";
			}
		}
		
		for (Appointment appointment2 : appointmentsPatient) {
			if((appointment.getStart().isBefore(appointment2.getStart()) || appointment.getStart().isEqual(appointment2.getStart()))
					&& (appointment2.getStart().isBefore(appointment.getEnd()) || appointment2.getStart().isEqual(appointment.getEnd()))) {
				return "This patient has already an appointment planned at that time!";
			}else if((appointment.getStart().isBefore(appointment2.getEnd()) || appointment.getStart().isEqual(appointment2.getEnd()))
					&& (appointment.getEnd().isAfter(appointment2.getEnd()) || appointment2.getEnd().isEqual(appointment.getEnd()))){
				return "This patient has already an appointment planned at that time!";
			}
		}
		
		repository.save(appointment);
		return "";
	}
	
}
