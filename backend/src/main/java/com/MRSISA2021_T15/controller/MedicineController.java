package com.MRSISA2021_T15.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import com.MRSISA2021_T15.model.SubstituteMedicine;
import com.MRSISA2021_T15.repository.MedicineRepository;
import com.MRSISA2021_T15.repository.SubstituteMedicineRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.MRSISA2021_T15.model.Medicine;
import com.MRSISA2021_T15.service.MedicineService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/medicine")
public class MedicineController {
	
	@Autowired
	private MedicineService medicineService;
	@Autowired
	private MedicineRepository medicineRepository;
	@Autowired
	private SubstituteMedicineRepository substituteMedicineRepository;



	@PostMapping(value = "/addMedicine", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	public ResponseEntity<String> addMedicine(@RequestBody Medicine medicine) {
		String message = medicineService.addMedicine(medicine);
		Gson gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<String>(gson.toJson("The medicine has been added successfully."), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping(path = "/{medicineId}/delete")
	@PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
	public void deleteMedicine(@PathVariable Integer medicineId) {

		for(SubstituteMedicine sm: substituteMedicineRepository.findAll())
		{
			if(sm.getMedicine().getId().equals( medicineId)) {
				sm.setMedicine(null);
			}
		}
		medicineRepository.deleteById(medicineId);
	}

	@GetMapping(value = "/getMedicineList", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	public HashMap<Integer, String> getMedicineList() {
		return medicineService.getMedicineList();
	}

	@RequestMapping(path="/all")
	@PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
	public @ResponseBody Iterable<Medicine> getAllMedicine() {
		return medicineRepository.findAll();
	}

	@RequestMapping(path="/{medicineId}/findById")
	@PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
	public Optional<Medicine> getMedicineById(@PathVariable Integer medicineId){
		return medicineRepository.findById(medicineId);
	}

	@RequestMapping(path="/{string}/findByString")
	@PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
	public ArrayList<Medicine> getMedicineByString(@PathVariable String string){
		Iterable<Medicine> medicineList = medicineRepository.findAll();
		ArrayList<Medicine> returnList = new ArrayList<>();
		for(Medicine medicine: medicineList){
			if(medicine.getName().toLowerCase().contains(string.toLowerCase())||
					medicine.getMedicineCode().toLowerCase().contains(string.toLowerCase())||
					medicine.getManufacturer().toLowerCase().contains(string.toLowerCase())||
					medicine.getMedicineType().toLowerCase().contains(string.toLowerCase())||
					//medicine.getAddtionalComments().toLowerCase().contains(string.toLowerCase())||
					medicine.getComposition().toLowerCase().contains(string.toLowerCase())||
					medicine.getForm().contains(string.toLowerCase()))
				returnList.add(medicine);
		}
		return returnList;
	}
	
	@PutMapping(path="/{medicineId}/update")
	@PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
	public ResponseEntity edit(@PathVariable Integer medicineId, @RequestBody Medicine m) throws NotFoundException {
		Medicine med = medicineRepository.findById(medicineId).orElseThrow(() -> new NotFoundException("Ne postoji id"));
		med.setName(m.getName());
		med.setAddtionalComments(m.getAddtionalComments());
		med.setComposition((m.getComposition()));
		med.setForm(m.getForm());
		med.setManufacturer(m.getManufacturer());
		med.setMedicineCode(m.getMedicineCode());
		med.setMedicineType(m.getMedicineType());
		medicineRepository.save(med);
		return ResponseEntity.ok().build();
	}
}