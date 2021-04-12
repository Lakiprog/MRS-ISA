package com.MRSISA2021_T15.controller;

import java.util.HashMap;

import com.MRSISA2021_T15.model.SubstituteMedicine;
import com.MRSISA2021_T15.repository.MedicineRepository;
import com.MRSISA2021_T15.repository.SubstituteMedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.MRSISA2021_T15.model.Medicine;
import com.MRSISA2021_T15.service.MedicineService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/medicine")
public class MedicineController {
	
	@Autowired
	private MedicineService medicineService;
	@Autowired
	private MedicineRepository medicineRepository;
	@Autowired
	private SubstituteMedicineRepository substituteMedicineRepository;



	@PostMapping(value = "/addMedicine", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addMedicine(@RequestBody Medicine medicine) {
		String message = medicineService.addMedicine(medicine);
		Gson gson = new GsonBuilder().create();
		if (message == "") {
			return new ResponseEntity<String>(gson.toJson("The medicine has been added successfully."), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping(path = "/{medicineId}/delete")
	public void deleteMedicine(@PathVariable Integer medicineId) {

		for(SubstituteMedicine sm: substituteMedicineRepository.findAll())
		{
			if(sm.getMedicine().getId() == medicineId) {
				sm.setMedicine(null);
			}
		}
		medicineRepository.deleteById(medicineId);
	}

	@GetMapping(value = "/getMedicineList", produces = MediaType.APPLICATION_JSON_VALUE)
	public HashMap<Integer, String> getMedicineList() {
		return medicineService.getMedicineList();
	}

	@RequestMapping(path="/all")
	public @ResponseBody Iterable<Medicine> getAllMedicine() {
		return medicineRepository.findAll();
	}
}