package com.MRSISA2021_T15.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.model.Employment;
import com.MRSISA2021_T15.model.MedicinePharmacy;
import com.MRSISA2021_T15.model.MedicineQuantity;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.model.Reservation;
import com.MRSISA2021_T15.model.ReservationItem;
import com.MRSISA2021_T15.repository.EmploymentRepository;
import com.MRSISA2021_T15.repository.MedicineQuantityRepository;
import com.MRSISA2021_T15.repository.ReservationItemRepository;
import com.MRSISA2021_T15.repository.ReservationRepository;

@Service
public class ReservationService {
	
	@Autowired
	ReservationRepository resRepo;
	@Autowired
	ReservationItemRepository resiRepo;
	@Autowired
	EmploymentRepository employRepo;
	@Autowired
	EmailSenderService emailsS;
	@Autowired
	Environment envir;
	@Autowired
	MedicineQuantityRepository medicineQuantityRepo;
	
	private static Integer reservationId = 1;

	public ArrayList<List<Object>> getReservations(Integer id){
		ArrayList<List<Object>> list = new ArrayList<>();
		ArrayList<Object> medicines = new ArrayList<>();
		ArrayList<Object> reservations = new ArrayList<>();
		Optional<Reservation> reservation = resRepo.findById(id);
		if(reservation.isEmpty()) {
			return list;
		}
		Pharmacist p = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Employment employment = employRepo.findByPharmacistId(p.getId());
		
		if(employment.getPharmacy().getId() != reservation.get().getPharmacy().getId()) {
			return list;
		}else if(reservation.get().getPickedUp() != null) {
			return list;
		} else if(LocalDateTime.now().isAfter(reservation.get().getEnd().minusDays(1))) {
			return list;
		}
		
		Iterable<ReservationItem> items = resiRepo.findAll();
		for (ReservationItem item : items) {
			if(item.getReservation().getId() == id) {
				medicines.add(item.getMedicine());
			}
		}
		reservations.add(reservation.get());
		list.add(medicines);
		list.add(reservations);
		return list;
	}
	
	public String giveOut(Reservation reservation) {
		reservation.setPickedUp(LocalDateTime.now());
		resRepo.save(reservation);
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(reservation.getPatient().getEmail());
		mailMessage.setSubject("Successfull pickup");
		mailMessage.setFrom(envir.getProperty("spring.mail.username"));
		mailMessage.setText("You have successfully picked up your medications contained in reservation with identifier " + reservation.getReservationId() 
		+ ". We hope you will visit us again at " + reservation.getPharmacy().getName() + ".");
		emailsS.sendEmail(mailMessage);
		
		return "";
	}
	
	
	public void saveReservation(MedicinePharmacy order, Patient patient, LocalDateTime date) {
		MedicineQuantity mq = new MedicineQuantity();
		mq.setMedicine(order.getMedicine());
		mq.setQuantity(order.getAmount());
		medicineQuantityRepo.save(mq);
		
		Reservation r = new Reservation();
		r.setPatient(patient);
		r.setPharmacy(order.getPharmacy());
		r.setEnd(date);
		r.setReservationId(reservationId.toString());
		reservationId++;
		r.setTotal(order.getCost());
		
		ReservationItem ri = new ReservationItem();
		ri.setMedicine(mq);
		
		ri.setReservation(r);
		
		resRepo.save(r);
		resiRepo.save(ri);
		
	}
	
	
}
