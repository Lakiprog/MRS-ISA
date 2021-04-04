package com.MRSISA2021_T15.service;

import java.util.HashMap;

import com.MRSISA2021_T15.model.Medicine;

public interface MedicineService {
	
	String addMedicine(Medicine medicine);
	
	HashMap<Integer, String> getMedicineList();
}
