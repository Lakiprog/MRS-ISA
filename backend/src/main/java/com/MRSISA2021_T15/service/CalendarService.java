package com.MRSISA2021_T15.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.model.Appointment;
import com.MRSISA2021_T15.repository.AppointmentRepository;
import com.MRSISA2021_T15.repository.CalendarRepository;


@Service
public class CalendarService {
	
	@Autowired
	private CalendarRepository repository;
	
	public List<Appointment> findAllPharmacist(Integer id){
		return repository.findAllPharmacistId(id);
	}
	
	public List<Appointment> findAllDermatologist(Integer id){
		return repository.findAllDermatologistId(id);
	}

}
