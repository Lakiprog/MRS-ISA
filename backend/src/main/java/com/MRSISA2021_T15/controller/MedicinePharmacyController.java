package com.MRSISA2021_T15.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MRSISA2021_T15.model.Medicine;
import com.MRSISA2021_T15.model.MedicinePharmacy;
import com.MRSISA2021_T15.model.MedicineQuantity;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.service.MedicinePharmacyService;
import com.MRSISA2021_T15.service.ReservationService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/medicinePharmacy")
public class MedicinePharmacyController {
	@Autowired
	MedicinePharmacyService mService;
	
	@Autowired
	ReservationService reservationService;

	@GetMapping(value = "/getMedicinePharmacist/pharmacy={pharmacyId}start={start}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	public List<MedicineQuantity> getMedicinePharmacist(@PathVariable("pharmacyId") Integer id, @PathVariable("start") String start) {
		List<MedicinePharmacy> medicines = mService.medcineInPharmacy(id);
		ArrayList<MedicineQuantity> meds = new ArrayList<>();
		for (MedicinePharmacy medicine : medicines) {
			if(medicine.getMedicine().getName().startsWith(start)) {
				MedicineQuantity med = new MedicineQuantity();
				med.setMedicine(medicine.getMedicine());
				med.setQuantity(medicine.getAmount());
				meds.add(med);
			}
		}
		return meds;
	}
	
	@GetMapping(value = "/getMedicineDermatologist/pharmacy={pharmacyId}start={start}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	public List<MedicineQuantity> getMedicineDermatologist(@PathVariable("pharmacyId") Integer id, @PathVariable("start") String start) {
		List<MedicinePharmacy> medicines = mService.medcineInPharmacy(id);
		ArrayList<MedicineQuantity> meds = new ArrayList<>();
		for (MedicinePharmacy medicine : medicines) {
			if(medicine.getMedicine().getName().startsWith(start)) {
				MedicineQuantity med = new MedicineQuantity();
				med.setMedicine(medicine.getMedicine());
				med.setQuantity(medicine.getAmount());
				meds.add(med);
			}
		}
		return meds;
	}
	
	@GetMapping(value = "/getAllMedicinePharmacy")
	public List<MedicinePharmacy> getAllMedicinePharmacy() {
		return mService.getAllMedicinePharmacy();
	}
	
	@GetMapping(value = "/searchMedicineByName/{name}")
	public List<MedicinePharmacy> searchMedicineByName(@PathVariable String name) {
		return mService.searchMedicineByName(name);
	}
	

	
	@PostMapping(value = "/orderMedicine", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	public ResponseEntity<String>orderMedicine(@RequestBody MedicinePharmacy order){
		
		String message = "";
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("123");
		System.out.println(patient.getId());
		
		System.out.println(order.getDate());
		
		
		mService.updateQuantity(order);
		reservationService.saveReservation(order, patient, order.getDate());
		
		
		Gson gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<String>(gson.toJson("You ordered your medicine. Thank you for purchase. :)"), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
