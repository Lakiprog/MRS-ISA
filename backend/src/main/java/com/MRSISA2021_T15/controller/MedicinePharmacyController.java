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

import com.MRSISA2021_T15.model.Medicine;
import com.MRSISA2021_T15.model.MedicinePharmacy;
import com.MRSISA2021_T15.model.MedicineQuantity;
import com.MRSISA2021_T15.service.MedicinePharmacyService;

@RestController
@RequestMapping("/medicinePharmacy")
public class MedicinePharmacyController {
	@Autowired
	MedicinePharmacyService mService;

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
}
