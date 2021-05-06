package com.MRSISA2021_T15.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.model.Absence;
import com.MRSISA2021_T15.model.Appointment;
import com.MRSISA2021_T15.model.AppointmentDermatologist;
import com.MRSISA2021_T15.model.AppointmentInfo;
import com.MRSISA2021_T15.model.AppointmentPharmacist;
import com.MRSISA2021_T15.model.EmploymentDermatologist;
import com.MRSISA2021_T15.model.EmploymentPharmacist;
import com.MRSISA2021_T15.model.MedicineAppointment;
import com.MRSISA2021_T15.model.MedicinePharmacy;
import com.MRSISA2021_T15.model.MedicineQuantity;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.model.Pharmacy;
import com.MRSISA2021_T15.model.Reservation;
import com.MRSISA2021_T15.model.ReservationItem;
import com.MRSISA2021_T15.repository.AbsenceRepository;
import com.MRSISA2021_T15.repository.AppointmentCreationRepository;
import com.MRSISA2021_T15.repository.AppointmentInfoRepository;
import com.MRSISA2021_T15.repository.AppointmentRepository;
import com.MRSISA2021_T15.repository.EmploymentRepository;
import com.MRSISA2021_T15.repository.MedicineAppointmentRepository;
import com.MRSISA2021_T15.repository.MedicinePharmacyRepository;
import com.MRSISA2021_T15.repository.MedicineQuantityRepository;
import com.MRSISA2021_T15.repository.ReservationItemRepository;
import com.MRSISA2021_T15.repository.ReservationRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentCreationRepository repository;
	@Autowired
	private EmploymentRepository repo2;
	@Autowired
	private AbsenceRepository repo3;
	@Autowired
	private MedicinePharmacyRepository repo4;
	@Autowired
	private MedicineQuantityRepository repo5;
	@Autowired
	private MedicineAppointmentRepository repo6;
	@Autowired
	private AppointmentInfoRepository repo7;
	@Autowired
	private ReservationRepository repo8;
	@Autowired
	private ReservationItemRepository repo9;

	public List<Appointment> findAllPharmacist(Integer id) {
		return repository.findAllPharmacistId(id);
	}

	public List<Appointment> findAllDermatologist(Integer id) {
		return repository.findAllDermatologistId(id);
	}

	public List<Appointment> findAllPatients(Integer id) {
		return repository.findAllPatientsId(id);
	}

	public List<Appointment> findAllAppointments() {
		return repository.findAll();
	}

	public Optional<Appointment> findAllAppointmentsId(Integer id) {
		return repository.findById(id);
	}

	public void makeTrue(Appointment appointment) {
		appointment.setDone(true);
		System.out.println(appointment.isDone());
		repository.save(appointment);
	}

	public String makeAppointmentPharmacist(AppointmentPharmacist appointment) {
		List<Appointment> appointmentsPatient = findAllPatients(appointment.getPatient().getId());
		List<Appointment> appointmentsPharmacist = findAllPharmacist(appointment.getPharmacist().getId());
		List<Absence> absences = repo3.findAllApproved();
		Pharmacist p = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmploymentPharmacist employment = repo2.findByPharmacistId(p.getId());

		if (appointment.getStart().getHour() < employment.getStart()
				|| appointment.getEnd().getHour() >= employment.getEnd()) {
			return "This pharmacist doesnt work at that time!";
		}

		for (Absence absence : absences) {
			if (absence.getDoctor().getId().equals(appointment.getPharmacist().getId())) {
				if (appointment.getStart().isEqual(absence.getStart())
						|| appointment.getStart().isEqual(absence.getEnd())) {
					return "This pharmacist is absent at that time!";
				} else if (appointment.getEnd().isEqual(absence.getStart())
						|| appointment.getEnd().isEqual(absence.getEnd())) {
					return "This pharmacist is absent at that time!";
				} else if (appointment.getStart().isAfter(absence.getStart())
						&& appointment.getStart().isBefore(absence.getEnd())) {
					return "This pharmacist is absent at that time!";
				} else if (appointment.getEnd().isBefore(absence.getEnd())
						&& appointment.getEnd().isAfter(absence.getStart())) {
					return "This pharmacist is absent at that time!";
				} else if (appointment.getStart().isBefore(absence.getStart())
						&& appointment.getEnd().isAfter(absence.getEnd())) {
					return "This pharmacist is absent at that time!";
				}
			}
		}

		for (Appointment appointment2 : appointmentsPharmacist) {
			if (appointment.getStart().isEqual(appointment2.getStart())
					|| appointment.getStart().isEqual(appointment2.getEnd())) {
				return "This pharmacist has already an appointment planned at that time!";
			} else if (appointment.getEnd().isEqual(appointment2.getStart())
					|| appointment.getEnd().isEqual(appointment2.getEnd())) {
				return "This pharmacist has already an appointment planned at that time!";
			} else if (appointment.getStart().isAfter(appointment2.getStart())
					&& appointment.getStart().isBefore(appointment2.getEnd())) {
				return "This pharmacist has already an appointment planned at that time!";
			} else if (appointment.getEnd().isBefore(appointment2.getEnd())
					&& appointment.getEnd().isAfter(appointment2.getStart())) {
				return "This pharmacist has already an appointment planned at that time!";
			} else if (appointment.getStart().isBefore(appointment2.getStart())
					&& appointment.getEnd().isAfter(appointment2.getEnd())) {
				return "This pharmacist has already an appointment planned at that time!";
			}
		}

		for (Appointment appointment2 : appointmentsPatient) {
			if (appointment.getStart().isEqual(appointment2.getStart())
					|| appointment.getStart().isEqual(appointment2.getEnd())) {
				return "This patient has already an appointment planned at that time!";
			} else if (appointment.getEnd().isEqual(appointment2.getStart())
					|| appointment.getEnd().isEqual(appointment2.getEnd())) {
				return "This patient has already an appointment planned at that time!";
			} else if (appointment.getStart().isAfter(appointment2.getStart())
					&& appointment.getStart().isBefore(appointment2.getEnd())) {
				return "This patient has already an appointment planned at that time!";
			} else if (appointment.getEnd().isBefore(appointment2.getEnd())
					&& appointment.getEnd().isAfter(appointment2.getStart())) {
				return "This patient has already an appointment planned at that time!";
			} else if (appointment.getStart().isBefore(appointment2.getStart())
					&& appointment.getEnd().isAfter(appointment2.getEnd())) {
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
			if (employment.getPharmacy().getId().equals(appointment.getPharmacy().getId())) {
				if (appointment.getStart().getHour() < employment.getStart()
						|| appointment.getEnd().getHour() >= employment.getEnd()) {
					return "This dermatologist doesnt work at that time!";
				}
				break;
			}
		}

		for (Absence absence : absences) {
			if (absence.getDoctor().getId().equals(appointment.getDermatologist().getId())) {
				if (appointment.getStart().isEqual(absence.getStart())
						|| appointment.getStart().isEqual(absence.getEnd())) {
					return "This dermatologist is absent at that time!";
				} else if (appointment.getEnd().isEqual(absence.getStart())
						|| appointment.getEnd().isEqual(absence.getEnd())) {
					return "This dermatologist is absent at that time!";
				} else if (appointment.getStart().isAfter(absence.getStart())
						&& appointment.getStart().isBefore(absence.getEnd())) {
					return "This dermatologist is absent at that time!";
				} else if (appointment.getEnd().isBefore(absence.getEnd())
						&& appointment.getEnd().isAfter(absence.getStart())) {
					return "This dermatologist is absent at that time!";
				} else if (appointment.getStart().isBefore(absence.getStart())
						&& appointment.getEnd().isAfter(absence.getEnd())) {
					return "This dermatologist is absent at that time!";
				}
			}
		}

		for (Appointment appointment2 : appointmentsPharmacist) {
			if (appointment.getStart().isEqual(appointment2.getStart())
					|| appointment.getStart().isEqual(appointment2.getEnd())) {
				return "This dermatologist has already an appointment planned at that time!";
			} else if (appointment.getEnd().isEqual(appointment2.getStart())
					|| appointment.getEnd().isEqual(appointment2.getEnd())) {
				return "This dermatologist has already an appointment planned at that time!";
			} else if (appointment.getStart().isAfter(appointment2.getStart())
					&& appointment.getStart().isBefore(appointment2.getEnd())) {
				return "This dermatologist has already an appointment planned at that time!";
			} else if (appointment.getEnd().isBefore(appointment2.getEnd())
					&& appointment.getEnd().isAfter(appointment2.getStart())) {
				return "This dermatologist has already an appointment planned at that time!";
			} else if (appointment.getStart().isBefore(appointment2.getStart())
					&& appointment.getEnd().isAfter(appointment2.getEnd())) {
				return "This dermatologist has already an appointment planned at that time!";
			}
		}

		for (Appointment appointment2 : appointmentsPatient) {
			if (appointment.getStart().isEqual(appointment2.getStart())
					|| appointment.getStart().isEqual(appointment2.getEnd())) {
				return "This patient has already an appointment planned at that time!";
			} else if (appointment.getEnd().isEqual(appointment2.getStart())
					|| appointment.getEnd().isEqual(appointment2.getEnd())) {
				return "This patient has already an appointment planned at that time!";
			} else if (appointment.getStart().isAfter(appointment2.getStart())
					&& appointment.getStart().isBefore(appointment2.getEnd())) {
				return "This patient has already an appointment planned at that time!";
			} else if (appointment.getEnd().isBefore(appointment2.getEnd())
					&& appointment.getEnd().isAfter(appointment2.getStart())) {
				return "This patient has already an appointment planned at that time!";
			} else if (appointment.getStart().isBefore(appointment2.getStart())
					&& appointment.getEnd().isAfter(appointment2.getEnd())) {
				return "This patient has already an appointment planned at that time!";
			}
		}

		repository.save(appointment);
		return "";
	}

	public String makeAppointmentDermatologistPredefined(Integer id, Patient patient) {
		Appointment appointment = repository.findWithId(id);
		if (appointment == null) {
			return "This appointment doesnt exist!";
		}
		if (appointment.getPatient() != null) {
			return "This appointment is already assigned";
		}
		appointment.setPatient(patient);
		repository.save(appointment);
		return "";
	}

	public List<Appointment> findAllPatientsId(Integer id) {
		return repository.findAllPatientsId(id);
	}

	public List<Patient> findAllPAwithPatientId(Integer id, Integer id2) {
		return repository.findAllPAwithPatientId(id, id2);
	}

	public List<Patient> findAllDAwithPatientId(Integer id, Integer id2) {
		return repository.findAllDAwithPatientId(id, id2);
	}

	public String endAppointment(Appointment appointment, MedicineQuantity[] meds, String comments) {
		String msg = "";
		
		for (MedicineQuantity medicine : meds) {
			for (MedicinePharmacy medicinePharm : repo4.findByPharmacyId(appointment.getPharmacy().getId())) {

				if (medicine.getMedicine().getId() == medicinePharm.getMedicine().getId()) {
					if (medicine.getQuantity() > medicinePharm.getAmount()) {
						msg = msg + "Your pharmacy has only " + medicinePharm.getAmount() + " of "
								+ medicine.getMedicine().getName() + ". ";
					}
					break;
				}

			}
		}
		
		if (msg.equals("")) {

			
			AppointmentInfo ai = new AppointmentInfo();
			ai.setAppointment(appointment);
			ai.setComments(comments);
			repo7.save(ai);
			
			Reservation r = new Reservation();
			r.setEnd(LocalDateTime.now().plusDays(30));
			r.setPatient(appointment.getPatient());
			r.setPharmacy(appointment.getPharmacy());
			r.setTotal(0);
			Reservation last = repo8.findFirstByOrderByIdDesc();
			if(last == null) {
				r.setReservationId("Res1");
			}else {
				r.setReservationId("Res" + (last.getId() + 1));	
			}
			repo8.save(r);
			
			for (MedicineQuantity medicine : meds) {
				for (MedicinePharmacy medicinePharm : repo4.findByPharmacyId(appointment.getPharmacy().getId())) {
					if (medicine.getMedicine().getId() == medicinePharm.getMedicine().getId()) {
						medicinePharm.setAmount(medicinePharm.getAmount() - medicine.getQuantity());
						repo4.save(medicinePharm);
						r.setTotal(r.getTotal() + medicinePharm.getCost()*medicine.getQuantity());
						break;
					}

				}
				repo5.save(medicine);
				
				MedicineAppointment ma = new MedicineAppointment();
				ma.setMedicine(medicine);
				ma.setAppointmentInfo(ai);
				repo6.save(ma);
				
				ReservationItem ri = new ReservationItem();
				ri.setMedicine(medicine);
				ri.setReservation(r);
				repo9.save(ri);
			}
			repo8.save(r);
		}
		return msg;
	}
	
	
	public List<AppointmentDermatologist>findAllFreeDermatologicalApp(){
		return repository.findAllFreeDermatologicalApp();
	}
	
	
	public void saveDerApp(AppointmentDermatologist app) {
		repository.save(app);
	}
	
	
	public List<AppointmentDermatologist> findAllDerAppWithPatientId(Integer id){
		return repository.findAllDerAppWithPatientId(id);
	}

}
