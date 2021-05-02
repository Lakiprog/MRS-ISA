package com.MRSISA2021_T15.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MRSISA2021_T15.model.Allergy;
import com.MRSISA2021_T15.model.Medicine;
import com.MRSISA2021_T15.model.MedicinePharmacy;
import com.MRSISA2021_T15.model.MedicineQuantity;
import com.MRSISA2021_T15.model.SubstituteMedicine;
import com.MRSISA2021_T15.service.AllergyService;
import com.MRSISA2021_T15.service.MedicinePharmacyService;
import com.MRSISA2021_T15.service.SubstituteMedicineService;

@RestController
@RequestMapping("/allergies")
public class AllergyController {

	@Autowired
	AllergyService service;
	
	@Autowired
	MedicinePharmacyService service1;
	
	@Autowired
	SubstituteMedicineService service2;
	
	@GetMapping(value = "/checkForAllergiesPharmacist/pharmacy={pharmacyId}medicine={medicineId}patient={patientId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_PHARMACIST')")
	public List<MedicineQuantity> getMedicinePharmacist(@PathVariable("pharmacyId") Integer id, @PathVariable("medicineId") Integer medicineId, @PathVariable("patientId") Integer patientId) {
		List<MedicinePharmacy> medps = service1.medcineInPharmacy(id);
		for (MedicinePharmacy medicine : medps) {
			if(medicine.getMedicine().getId() == medicineId) {
				if(medicine.getAmount() == 0) {
					//TODO send notification to admin
					return dummySubs(medicineId, patientId, medps);
				}
				break;
			}
		}
		List<Allergy> allergies = service.getForPatient(patientId);
		for (Allergy allergy : allergies) {
			if(allergy.getMedicine().getId() == medicineId) {
				return dummySubs(medicineId, patientId, medps);
			}
		}
		return new ArrayList<MedicineQuantity>();
	}
	
	@GetMapping(value = "/checkForAllergiesDermatologist/pharmacy={pharmacyId}medicine={medicineId}patient={patientId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_DERMATOLOGIST')")
	public List<MedicineQuantity> getMedicineDermatologist(@PathVariable("pharmacyId") Integer id, @PathVariable("medicineId") Integer medicineId, @PathVariable("patientId") Integer patientId) {
		List<MedicinePharmacy> medps = service1.medcineInPharmacy(id);
		for (MedicinePharmacy medicine : medps) {
			if(medicine.getMedicine().getId() == medicineId) {
				if(medicine.getAmount() == 0) {
					//TODO send notification to admin
					return dummySubs(medicineId, patientId, medps);
				}
				break;
			}
		}
		List<Allergy> allergies = service.getForPatient(patientId);
		for (Allergy allergy : allergies) {
			if(allergy.getMedicine().getId() == medicineId) {
				return dummySubs(medicineId, patientId, medps);
			}
		}
		return new ArrayList<MedicineQuantity>();
	}
	
	public List<MedicineQuantity> dummySubs(Integer medicineId, Integer patientId, List<MedicinePharmacy> medps){
		boolean allergic = false;
		List<Medicine> subs1 = service2.getForMedicine(medicineId);
		ArrayList<MedicineQuantity> subs = new ArrayList<>();
		List<Allergy> allergies = service.getForPatient(patientId);
		for (Medicine medicine : subs1) {
			for (Allergy allergy : allergies) {
				if(allergy.getMedicine().getId() == medicine.getId()) {
					allergic = true;
					break;
				}
			}
			if(!allergic) {
				for(MedicinePharmacy m : medps) {
					if(m.getMedicine().getId() == medicine.getId()) {
						MedicineQuantity med = new MedicineQuantity();
						med.setMedicine(medicine);
						med.setQuantity(m.getAmount());
						subs.add(med);
					}
				}				
			}
			allergic = false;
		}
		if(subs.size() == 0) {
			MedicineQuantity dummy = new MedicineQuantity();
			dummy.setMedicine(new Medicine());
			dummy.getMedicine().setName("ERROR");
			subs.add(dummy);
		}
		return subs;
	}
}
