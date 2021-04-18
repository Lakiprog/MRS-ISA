package com.MRSISA2021_T15.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import com.MRSISA2021_T15.model.*;
import com.MRSISA2021_T15.repository.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	@Autowired
	private AllergyRepository allergyRepository;
	@Autowired
	private MedicinePharmacyRepository medicinePharmacyRepository;
	@Autowired
	private PharmacyRepository pharmacyRepository;


	@PostMapping(value = "/addMedicine", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
	public void deleteMedicine(@PathVariable Integer medicineId) {
/*
		for(SubstituteMedicine sm: substituteMedicineRepository.findAll())
		{
			if(sm.getMedicine().getId().equals( medicineId)) {
				//substituteMedicineRepository.delete(sm);
				/*System.out.println("SUBSTITUTE MEDICINE: "+sm);
				System.out.println(sm.getMedicine().getId());
				System.out.println(medicineId);
				sm.setMedicine(null);
				substituteMedicineRepository.save(sm);
				substituteMedicineRepository.delete(sm);
			}
		}
		for(Allergy al: allergyRepository.findAll())
		{
			if(al.getMedicine().getId().equals(medicineId)){
				allergyRepository.delete(al);
			}
		}
		for(MedicinePharmacy mp: medicinePharmacyRepository.findAll()){
			if(mp.getMedicine().getId().equals(medicineId)){

				mp.setPharmacy(null);

				medicinePharmacyRepository.save(mp);
				medicinePharmacyRepository.delete(mp);
			}
		}*/
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

	@RequestMapping(path="/{medicineId}/findById")
	public Optional<Medicine> getMedicineById(@PathVariable Integer medicineId){
		return medicineRepository.findById(medicineId);
	}

	@RequestMapping(path="/{string}/findByString")
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
	public
	ResponseEntity edit(@PathVariable Integer medicineId, @RequestBody Medicine m) throws NotFoundException {
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