package com.MRSISA2021_T15.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.model.Absence;
import com.MRSISA2021_T15.model.Appointment;
import com.MRSISA2021_T15.model.AppointmentDermatologist;
import com.MRSISA2021_T15.model.AppointmentPharmacist;
import com.MRSISA2021_T15.model.EmploymentDermatologist;
import com.MRSISA2021_T15.model.EmploymentPharmacist;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.repository.AbsenceRepository;
import com.MRSISA2021_T15.repository.AppointmentCreationRepository;
import com.MRSISA2021_T15.repository.EmploymentRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentCreationRepository repository;
	@Autowired
	private EmploymentRepository repo2;
	@Autowired
	private AbsenceRepository repo3;
	
	public List<Appointment> findAllPharmacist(Integer id){
		return repository.findAllPharmacistId(id);
	}
	
	public List<Appointment> findAllDermatologist(Integer id){
		return repository.findAllDermatologistId(id);
	}
	
	public List<Appointment> findAllPatients(Integer id){
		return repository.findAllPatientsId(id);
	}
	
	public List<Appointment>findAllAppointments(){
		return repository.findAll();
	}
	
	public String makeAppointmentPharmacist(AppointmentPharmacist appointment) {
		List<Appointment> appointmentsPatient = findAllPatients(appointment.getPatient().getId());
		List<Appointment> appointmentsPharmacist = findAllPharmacist(appointment.getPharmacist().getId());
		List<Absence> absences = repo3.findAllApproved();
		Pharmacist p = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmploymentPharmacist employment = repo2.findByPharmacistId(p.getId());
		
		if(appointment.getStart().getHour() < employment.getStart() || appointment.getEnd().getHour() >= employment.getEnd()) {
			return "This pharmacist doesnt work at that time!";
		}
		
		for (Absence absence : absences) {
			if(absence.getDoctor().getId().equals(appointment.getPharmacist().getId())) {
				if(appointment.getStart().isEqual(absence.getStart())  || appointment.getStart().isEqual(absence.getEnd())) {
					return "This pharmacist is absent at that time!";
				}else if(appointment.getEnd().isEqual(absence.getStart())  || appointment.getEnd().isEqual(absence.getEnd())) {
					return "This pharmacist is absent at that time!";
				}else if(appointment.getStart().isAfter(absence.getStart()) && appointment.getStart().isBefore(absence.getEnd())) {
					return "This pharmacist is absent at that time!";
				}else if(appointment.getEnd().isBefore(absence.getEnd()) && appointment.getEnd().isAfter(absence.getStart())) {
					return "This pharmacist is absent at that time!";
				}else if(appointment.getStart().isBefore(absence.getStart()) && appointment.getEnd().isAfter(absence.getEnd())) {
					return "This pharmacist is absent at that time!";
				}
			}
		}
		
		for (Appointment appointment2 : appointmentsPharmacist) {
			if(appointment.getStart().isEqual(appointment2.getStart())  || appointment.getStart().isEqual(appointment2.getEnd())) {
				return "This pharmacist has already an appointment planned at that time!";
			}else if(appointment.getEnd().isEqual(appointment2.getStart())  || appointment.getEnd().isEqual(appointment2.getEnd())) {
				return "This pharmacist has already an appointment planned at that time!";
			}else if(appointment.getStart().isAfter(appointment2.getStart()) && appointment.getStart().isBefore(appointment2.getEnd())) {
				return "This pharmacist has already an appointment planned at that time!";
			}else if(appointment.getEnd().isBefore(appointment2.getEnd()) && appointment.getEnd().isAfter(appointment2.getStart())) {
				return "This pharmacist has already an appointment planned at that time!";
			}else if(appointment.getStart().isBefore(appointment2.getStart()) && appointment.getEnd().isAfter(appointment2.getEnd())) {
				return "This pharmacist has already an appointment planned at that time!";
			}
		}
		
		for (Appointment appointment2 : appointmentsPatient) {
			if(appointment.getStart().isEqual(appointment2.getStart())  || appointment.getStart().isEqual(appointment2.getEnd())) {
				return "This patient has already an appointment planned at that time!";
			}else if(appointment.getEnd().isEqual(appointment2.getStart())  || appointment.getEnd().isEqual(appointment2.getEnd())) {
				return "This patient has already an appointment planned at that time!";
			}else if(appointment.getStart().isAfter(appointment2.getStart()) && appointment.getStart().isBefore(appointment2.getEnd())) {
				return "This patient has already an appointment planned at that time!";
			}else if(appointment.getEnd().isBefore(appointment2.getEnd()) && appointment.getEnd().isAfter(appointment2.getStart())) {
				return "This patient has already an appointment planned at that time!";
			}else if(appointment.getStart().isBefore(appointment2.getStart()) && appointment.getEnd().isAfter(appointment2.getEnd())) {
				return "This patient has already an appointment planned at that time!";
			}
		}
		
		repository.save(appointment);
		return "";
	}
	
	public String makeAppointmentDermatologist(AppointmentDermatologist appointment) {
		List<Appointment> appointmentsPatient = findAllPatients(appointment.getPatient().getId());
		List<Appointment> appointmentsPharmacist = findAllDermatologist(appointment.getDermatologist().getId());
		List<Absence> absences = repo3.findAllApproved();
		
		for (EmploymentDermatologist employment : repo2.findByDermatologistId(appointment.getDermatologist().getId())) {
			if(employment.getPharmacy().getId().equals(appointment.getPharmacy().getId())) {
				if(appointment.getStart().getHour() < employment.getStart() || appointment.getEnd().getHour() >= employment.getEnd()) {
					return "This dermatologist doesnt work at that time!";
				}
				break;
			}
		}
		
		for (Absence absence : absences) {
			if(absence.getDoctor().getId().equals(appointment.getDermatologist().getId())) {
				if(appointment.getStart().isEqual(absence.getStart())  || appointment.getStart().isEqual(absence.getEnd())) {
					return "This dermatologist is absent at that time!";
				}else if(appointment.getEnd().isEqual(absence.getStart())  || appointment.getEnd().isEqual(absence.getEnd())) {
					return "This dermatologist is absent at that time!";
				}else if(appointment.getStart().isAfter(absence.getStart()) && appointment.getStart().isBefore(absence.getEnd())) {
					return "This dermatologist is absent at that time!";
				}else if(appointment.getEnd().isBefore(absence.getEnd()) && appointment.getEnd().isAfter(absence.getStart())) {
					return "This dermatologist is absent at that time!";
				}else if(appointment.getStart().isBefore(absence.getStart()) && appointment.getEnd().isAfter(absence.getEnd())) {
					return "This dermatologist is absent at that time!";
				}
			}
		}
		
		for (Appointment appointment2 : appointmentsPharmacist) {
			if(appointment.getStart().isEqual(appointment2.getStart())  || appointment.getStart().isEqual(appointment2.getEnd())) {
				return "This dermatologist has already an appointment planned at that time!";
			}else if(appointment.getEnd().isEqual(appointment2.getStart())  || appointment.getEnd().isEqual(appointment2.getEnd())) {
				return "This dermatologist has already an appointment planned at that time!";
			}else if(appointment.getStart().isAfter(appointment2.getStart()) && appointment.getStart().isBefore(appointment2.getEnd())) {
				return "This dermatologist has already an appointment planned at that time!";
			}else if(appointment.getEnd().isBefore(appointment2.getEnd()) && appointment.getEnd().isAfter(appointment2.getStart())) {
				return "This dermatologist has already an appointment planned at that time!";
			}else if(appointment.getStart().isBefore(appointment2.getStart()) && appointment.getEnd().isAfter(appointment2.getEnd())) {
				return "This dermatologist has already an appointment planned at that time!";
			}
		}
		
		for (Appointment appointment2 : appointmentsPatient) {
			if(appointment.getStart().isEqual(appointment2.getStart())  || appointment.getStart().isEqual(appointment2.getEnd())) {
				return "This patient has already an appointment planned at that time!";
			}else if(appointment.getEnd().isEqual(appointment2.getStart())  || appointment.getEnd().isEqual(appointment2.getEnd())) {
				return "This patient has already an appointment planned at that time!";
			}else if(appointment.getStart().isAfter(appointment2.getStart()) && appointment.getStart().isBefore(appointment2.getEnd())) {
				return "This patient has already an appointment planned at that time!";
			}else if(appointment.getEnd().isBefore(appointment2.getEnd()) && appointment.getEnd().isAfter(appointment2.getStart())) {
				return "This patient has already an appointment planned at that time!";
			}else if(appointment.getStart().isBefore(appointment2.getStart()) && appointment.getEnd().isAfter(appointment2.getEnd())) {
				return "This patient has already an appointment planned at that time!";
			}
		}
		
		repository.save(appointment);
		return "";
	}

	public String makeAppointmentDermatologistPredefined(Integer id, Patient patient) {
		Appointment appointment = repository.findWithId(id);
		if(appointment == null) {
			return "This appointment doesnt exist!";
		}if(appointment.getPatient() != null) {
			return "This appointment is already assigned";
		}
		appointment.setPatient(patient);
		repository.save(appointment);
		return "";
	}
	
	
	public List<Appointment> findAllPatientsId(Integer id){
		return repository.findAllPatientsId(id);
	}
	
	public List<Patient> findAllPAwithPatientId(Integer id, Integer id2){
		return repository.findAllPAwithPatientId(id, id2);
	}
	
	public List<Patient> findAllDAwithPatientId(Integer id, Integer id2){
		return repository.findAllDAwithPatientId(id, id2);
	}
	
}
