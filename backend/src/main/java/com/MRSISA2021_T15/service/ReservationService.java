package com.MRSISA2021_T15.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.model.Employment;
import com.MRSISA2021_T15.model.MedicineQuantity;
import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.model.Reservation;
import com.MRSISA2021_T15.model.ReservationItem;
import com.MRSISA2021_T15.repository.EmploymentRepository;
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
		return "";
	}
}
