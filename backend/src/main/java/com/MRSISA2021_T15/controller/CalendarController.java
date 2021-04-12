package com.MRSISA2021_T15.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MRSISA2021_T15.model.Appointment;
import com.MRSISA2021_T15.model.Event;
import com.MRSISA2021_T15.service.CalendarService;

@RestController
@RequestMapping("/calendar")
public class CalendarController {

	@Autowired
	private  CalendarService service;
	
	@GetMapping(value = "/calendarPharmacist/{pharmacistId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Event> calendarPharmacist(@PathVariable("pharmacistId") Integer pharmacistId){
		List<Appointment> appointments = service.findAllPharmacist(pharmacistId);
		ArrayList<Event> events = new ArrayList<Event>();
		for (Appointment appointment : appointments) {
			Event event = new Event();
			event.setTitle("Appointment with" + " " + appointment.getPatient().getSurname() + " " +  appointment.getPatient().getName());
			event.setStart(appointment.getStart());
			event.setEnd(appointment.getEnd());
			events.add(event);
		}
		return events;
	}
	
	@GetMapping(value = "/calendarDermatologist/id={dermatologistId}pharmacy={pharmacyId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Event> calendarDermatologist(@PathVariable("dermatologistId") Integer dermatologistId, @PathVariable("pharmacyId") Integer pharmacyId){
		List<Appointment> appointments = service.findAllDermatologist(dermatologistId);
		ArrayList<Event> events = new ArrayList<Event>();
		for (Appointment appointment : appointments) {
			if(appointment.getPharmacy().getId() == pharmacyId) {
				Event event = new Event();
				if(appointment.getPatient() == null) {
					event.setTitle("Predefined Appointment in " + appointment.getPharmacy().getName());
				}else {
					event.setTitle("Appointment with " + appointment.getPatient().getSurname() + " " +  appointment.getPatient().getName() + " in " + appointment.getPharmacy().getName());
				}
				event.setStart(appointment.getStart());
				event.setEnd(appointment.getEnd());
				events.add(event);
			}
		}
		return events;
	}
	
	@GetMapping(value = "/calendarDermatologist/{dermatologistId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Event> calendarDermatologist(@PathVariable("dermatologistId") Integer dermatologistId){
		List<Appointment> appointments = service.findAllDermatologist(dermatologistId);
		ArrayList<Event> events = new ArrayList<Event>();
		for (Appointment appointment : appointments) {
				Event event = new Event();
				if(appointment.getPatient() == null) {
					event.setTitle("Predefined Appointment in " + appointment.getPharmacy().getName());
				}else {
					event.setTitle("Appointment with " + appointment.getPatient().getSurname() + " " +  appointment.getPatient().getName() + " in " + appointment.getPharmacy().getName());
				}
				event.setStart(appointment.getStart());
				event.setEnd(appointment.getEnd());
				events.add(event);
		}
		return events;
	}
	
	@GetMapping(value = "/calendarDermatologistPredefined/{dermatologistId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Event> calendarDermatologistPredefined(@PathVariable("dermatologistId") Integer dermatologistId){
		List<Appointment> appointments = service.findAllDermatologist(dermatologistId);
		ArrayList<Event> events = new ArrayList<Event>();
		for (Appointment appointment : appointments) {
				if(appointment.getPatient() == null) {
					Event event = new Event();
					event.setTitle("Predefined Appointment in " + appointment.getPharmacy().getName());
					event.setStart(appointment.getStart());
					event.setEnd(appointment.getEnd());
					events.add(event);
				}
		}
		return events;
	}
	
	@GetMapping(value = "/calendarDermatologistPredefined/id={dId}/pharmacy={pId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Event> calendarDermatologistPredefined(@PathVariable("dId") Integer dermatologistId, @PathVariable("pId") Integer pharmacyId){
		List<Appointment> appointments = service.findAllDermatologist(dermatologistId);
		ArrayList<Event> events = new ArrayList<Event>();
		for (Appointment appointment : appointments) {
			if(appointment.getPharmacy().getId() == pharmacyId && appointment.getPatient() == null) {
				Event event = new Event();
				event.setId(appointment.getId());
				event.setTitle("Predefined Appointment in " + appointment.getPharmacy().getName());
				event.setStart(appointment.getStart());
				event.setEnd(appointment.getEnd());
				events.add(event);
			}
		}
		return events;
	}
}
