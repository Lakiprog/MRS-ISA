package com.MRSISA2021_T15.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.model.Medicine;
import com.MRSISA2021_T15.repository.MedicineRepository;

@Service
public class MedicineServiceImpl implements MedicineService {
	
	@Autowired
	private MedicineRepository medicineRepository;

	@Override
	public String addMedicine(Medicine medicine) {
		String message = "";
		if (medicineRepository.findByMedicineCode(medicine.getMedicineCode()) != null) {
			message = "A medicine with this code already exists!";
		} else if (medicineRepository.findByName(medicine.getName()) != null) {
			message = "A medicine with this name already exists!";
		} else {
			medicineRepository.save(medicine);
		}
		return message;
	}

	@Override
	public ArrayList<String> getMedicineNames() {
		return medicineRepository.findAllName();
	}

}
