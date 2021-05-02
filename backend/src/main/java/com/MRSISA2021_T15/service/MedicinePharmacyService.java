package com.MRSISA2021_T15.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.model.MedicinePharmacy;
import com.MRSISA2021_T15.repository.MedicinePharmacyRepository;

@Service
public class MedicinePharmacyService {

	@Autowired
	private MedicinePharmacyRepository repo;
	
	public List<MedicinePharmacy> medcineInPharmacy(Integer id) {
		return repo.findByPharmacyId(id);
	}
	
	public MedicinePharmacy medcineExact(Integer pharmacy, Integer medicine) {
		return repo.findByExact(pharmacy, medicine);
	}
}
