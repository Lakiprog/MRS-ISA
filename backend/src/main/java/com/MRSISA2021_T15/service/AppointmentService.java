package com.MRSISA2021_T15.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.model.Absence;
import com.MRSISA2021_T15.model.Allergy;
import com.MRSISA2021_T15.model.Appointment;
import com.MRSISA2021_T15.model.AppointmentDermatologist;
import com.MRSISA2021_T15.model.AppointmentInfo;
import com.MRSISA2021_T15.model.AppointmentPharmacist;
import com.MRSISA2021_T15.model.CanceledPharAppoinment;
import com.MRSISA2021_T15.model.Category;
import com.MRSISA2021_T15.model.CategoryName;
import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.EmploymentDermatologist;
import com.MRSISA2021_T15.model.EmploymentPharmacist;
import com.MRSISA2021_T15.model.MedicineAppointment;
import com.MRSISA2021_T15.model.MedicinePharmacy;
import com.MRSISA2021_T15.model.MedicineQuantity;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.model.Reservation;
import com.MRSISA2021_T15.model.ReservationItem;
import com.MRSISA2021_T15.repository.AbsenceRepository;
import com.MRSISA2021_T15.repository.AllergyRepository;
import com.MRSISA2021_T15.repository.AppointmentConsultationPointsRepository;
import com.MRSISA2021_T15.repository.AppointmentCreationRepository;
import com.MRSISA2021_T15.repository.AppointmentInfoRepository;
import com.MRSISA2021_T15.repository.AppointmentRepository;
import com.MRSISA2021_T15.repository.CanceledPharmaAppointmentRepository;
import com.MRSISA2021_T15.repository.CategoryRepository;
import com.MRSISA2021_T15.repository.EmploymentRepository;
import com.MRSISA2021_T15.repository.MedicineAppointmentRepository;
import com.MRSISA2021_T15.repository.MedicinePharmacyRepository;
import com.MRSISA2021_T15.repository.MedicineQuantityRepository;
import com.MRSISA2021_T15.repository.MedicineRepository;
import com.MRSISA2021_T15.repository.ReservationItemRepository;
import com.MRSISA2021_T15.repository.ReservationRepository;
import com.MRSISA2021_T15.repository.UserRepository;

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
	@Autowired
	EmailSenderService emailsend;
	@Autowired
	Environment en;
  @Autowired
  private CategoryRepository categoryRepository;
	@Autowired
	private AppointmentConsultationPointsRepository appointmentConsultationPointsRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private CanceledPharmaAppointmentRepository canceledRepository;
	@Autowired
	private AllergyRepository allergyrepo;
	@Autowired
	private MedicineRepository medicineRepository;

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
	
	public List<EmploymentDermatologist> employmentsDermatologist(){
		Dermatologist p = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return repo2.findByDermatologistId(p.getId());
	}

	public void makeTrue(Appointment appointment) {
		appointment.setDone(true);
		System.out.println(appointment.isDone());
		repository.save(appointment);
	}

	public String makeAppointmentPharmacist(AppointmentPharmacist appointment) {
		
		if(appointment.getStart().isAfter(appointment.getEnd())) {
			return "Start can't be after end!";	
		}else if(appointment.getStart().isBefore(LocalDateTime.now()) || appointment.getStart().isEqual(LocalDateTime.now())) {
			return "Can't schedule appointment into past!";
		}
		
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
		sendEmailAppointment(appointment);
		return "";
	}

	public String makeAppointmentDermatologist(AppointmentDermatologist appointment) {
		
		if(appointment.getStart().isAfter(appointment.getEnd())) {
			return "Start can't be after end!";	
		}else if(appointment.getStart().isBefore(LocalDateTime.now()) || appointment.getStart().isEqual(LocalDateTime.now())) {
			return "Can't schedule appointment into past!";
		}
		
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
		sendEmailAppointment(appointment);
		return "";
	}

	public String makeAppointmentDermatologistPredefined(Integer id, Patient patient) {
		Appointment appointment = repository.findWithId(id);
		
		if (appointment == null) {
			return "This appointment doesnt exist!";
		}else if(appointment.getStart().isAfter(appointment.getEnd())) {
			return "Start can't be after end!";	
		}else if(appointment.getStart().isBefore(LocalDateTime.now()) || appointment.getStart().isEqual(LocalDateTime.now())) {
			return "Can't schedule appointment into past!";
		}else if (appointment.getPatient() != null) {
			return "This appointment is already assigned";
		}
		appointment.setPatient(patient);
		repository.save(appointment); //prepravi da ga nadjes
		sendEmailAppointment(appointment);
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
		
		List<Allergy> allergies =  allergyrepo.findAllPatients(appointment.getPatient().getId());
		
		for (MedicineQuantity medicine : meds) {
			
			for (Allergy allergy : allergies) {
				if(allergy.getMedicine().getId() == medicine.getMedicine().getId()) {
					msg = "Patient is allergic to this medicine!";
					break;
				}
			}
			
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
			Patient patient = (Patient) userRepository.findById(appointment.getPatient().getId()).get();
			for (MedicineQuantity medicine : meds) {
				patient.setCollectedPoints(Math.abs(patient.getCollectedPoints()) + 
						(medicineRepository.getPointsByMedicineCode(medicine.getMedicine().getMedicineCode()) * Math.abs(medicine.getQuantity())));
			}
			if (appointment instanceof AppointmentDermatologist) {
				if (appointmentConsultationPointsRepository.getPointsByType("APPOINTMENT") != null) {
					patient.setCollectedPoints(patient.getCollectedPoints() + Math.abs(appointmentConsultationPointsRepository.getPointsByTypePessimisticWrite("APPOINTMENT")));
				}
			}
			else if (appointment instanceof AppointmentPharmacist) {
				if (appointmentConsultationPointsRepository.getPointsByType("CONSULTATION") != null) {
					patient.setCollectedPoints(patient.getCollectedPoints() + Math.abs(appointmentConsultationPointsRepository.getPointsByTypePessimisticWrite("CONSULTATION")));
				}
			}
			
			if (patient.getCategoryName().equals(CategoryName.REGULAR)) {
				Category c = categoryRepository.findByCategoryNamePessimisticWrite(CategoryName.SILVER);
				if (c != null) {
					if (patient.getCollectedPoints() >= Math.abs(c.getRequiredNumberOfPoints())) {
						patient.setCategoryName(CategoryName.SILVER);
					}
				}
			} else if (patient.getCategoryName().equals(CategoryName.SILVER)) {
				Category c1 = categoryRepository.findByCategoryNamePessimisticWrite(CategoryName.GOLD);
				Category c2 = categoryRepository.findByCategoryNamePessimisticWrite(CategoryName.SILVER);
				if (c1 != null) {
					if (patient.getCollectedPoints() >= Math.abs(c1.getRequiredNumberOfPoints())) {
						patient.setCategoryName(CategoryName.GOLD);
					}
				}
				if (c2 != null) {
					appointment.setDiscount((100.0 - Math.abs(c2.getDiscount())) / 100.0);
				}
			} else if (patient.getCategoryName().equals(CategoryName.GOLD)) {
				Category c1 = categoryRepository.findByCategoryNamePessimisticWrite(CategoryName.GOLD);
				if (c1 != null) {
					appointment.setDiscount((100.0 - Math.abs(c1.getDiscount())) / 100.0);
				}
			}
			
			appointment.setPatient(patient);
			appointmentRepository.save(appointment);
			userRepository.save(patient);
			
			ai.setAppointment(appointment);
			ai.setComments(comments);
			repo7.save(ai);
			
			Reservation r = new Reservation();
			if(meds.length != 0) {
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
				
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(appointment.getPatient().getEmail());
				mailMessage.setSubject("Medication reservation");
				mailMessage.setFrom(en.getProperty("spring.mail.username"));
				mailMessage.setText("Medication has been reserved for you in pharmacy " + appointment.getPharmacy().getName() +  ". You can pick it up till one day before " 
				+ r.getEnd() + ". When you come pick it up, you will have to give the pharmacist this identifier " + r.getReservationId() + ". Have a nice day!");
				emailsend.sendEmail(mailMessage);
			}
			
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
	
	public List<AppointmentPharmacist> findAllPharAppWithPatientId(Integer id){
		return repository.findAllPharAppWithPatientId(id);
	}

	public void sendEmailAppointment(Appointment appointment) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(appointment.getPatient().getEmail());
		mailMessage.setSubject("New appointment scheduled");
		mailMessage.setFrom(en.getProperty("spring.mail.username"));
		mailMessage.setText("New appointment scheduled in pharmacy " + appointment.getPharmacy().getName() 
				+ ". It is scheduled to be from " + appointment.getStart() + " to " + appointment.getEnd() + ". Have a nice day!");
		emailsend.sendEmail(mailMessage);
	}
	
	
	public List<AppointmentPharmacist> getPharmacisApp() {
		List<AppointmentPharmacist> l = appointmentRepository.getAllPharmacistApp();
		System.out.println("mama");
		for(int i = 0; i<l.size(); i++) {
			System.out.println(l.get(i).getId());
		}
		return appointmentRepository.getAllPharmacistApp();
	}
	
	
	public void newPharmaciesApp(AppointmentPharmacist appoinment) {
		appoinment.setPrice(1000);
		appointmentRepository.save(appoinment);
		
	}
	
	
	public void deleteDermatologicalApp(AppointmentDermatologist appointment) {
		Appointment app = appointmentRepository.findDerAppWithId(appointment.getId());
		app.setPatient(null);
	}
	
	
	//ovdje stavi upis u onu tabelu
	public void deletePharmaciestApp(AppointmentPharmacist appoinment) {
		appointmentRepository.delete(appoinment);
		
		CanceledPharAppoinment canceled = new CanceledPharAppoinment();
		canceled.setPatient(appoinment.getPatient());
		canceled.setPharmacist(appoinment.getPharmacist());
		canceled.setPharmacy(appoinment.getPharmacy());
		canceled.setStart(appoinment.getStart());
		canceled.setEnd(appoinment.getEnd());
		
		canceledRepository.save(canceled);
		
	}
	
	

}
