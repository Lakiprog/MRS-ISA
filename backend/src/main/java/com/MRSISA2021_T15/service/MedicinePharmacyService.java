package com.MRSISA2021_T15.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.AuthenticationManagerBeanDefinitionParser;
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
	
	public List<MedicinePharmacy> getAllMedicinePharmacy() {
		return (List<MedicinePharmacy>) repo.findAll();
	}
	
	public List<MedicinePharmacy> searchMedicineByName(String name) {
		List<MedicinePharmacy> retVal = new ArrayList<MedicinePharmacy>();
		List<MedicinePharmacy> allMedicinePharmacy = (List<MedicinePharmacy>) repo.findAll();
		for (MedicinePharmacy mp: allMedicinePharmacy) {
			if (mp.getMedicine().getName().toLowerCase().contains(name.toLowerCase())) {
				retVal.add(mp);
			}
		}
		return retVal;
	}
	
	public void updateQuantity(MedicinePharmacy mp) {
		MedicinePharmacy mpb = new MedicinePharmacy();
		Integer number = mpb.getAmount() - mp.getAmount();
		mpb.setAmount(number);
		repo.save(mpb);
	}
}
