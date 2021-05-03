package com.MRSISA2021_T15.service;

import java.util.HashMap;
import java.util.HashSet;

import com.MRSISA2021_T15.model.Medicine;
import com.MRSISA2021_T15.model.MedicineType;

public interface MedicineService {
	
	String addMedicine(Medicine medicine);
	
	HashMap<Integer, String> getMedicineList();
	
	HashSet<MedicineType> getMedicineTypes();
}
