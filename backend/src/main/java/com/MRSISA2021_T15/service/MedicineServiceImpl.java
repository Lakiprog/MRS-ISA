package com.MRSISA2021_T15.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MRSISA2021_T15.model.Medicine;
import com.MRSISA2021_T15.model.SubstituteMedicine;
import com.MRSISA2021_T15.repository.MedicineRepository;
import com.MRSISA2021_T15.repository.SubstituteMedicineRepository;

@Service
public class MedicineServiceImpl implements MedicineService {
	
	@Autowired
	private MedicineRepository medicineRepository;
	
	@Autowired
	private SubstituteMedicineRepository substituteMedicineRepository;

	@Transactional
	@Override
	public String addMedicine(Medicine medicine) {
		String message = "";
		if (medicineRepository.findByMedicineCode(medicine.getMedicineCode()) != null) {
			message = "A medicine with this code already exists!";
		} else {
			medicineRepository.save(medicine);
			var substituteMedicineIds = medicine.getSubstituteMedicineIds();
			if (substituteMedicineIds != null) {
				for (Integer id : substituteMedicineIds) {
					SubstituteMedicine substituteMedicine = new SubstituteMedicine();
					substituteMedicine.setMedicine(medicine);
					var sm = medicineRepository.findById(id);
					if (sm.get() != null) {
						substituteMedicine.setSubstituteMedicine(sm.get());
						substituteMedicineRepository.save(substituteMedicine);
					}
				}
			}
		}
		return message;
	}

	@Override
	public HashMap<Integer, String> getMedicineList() {
		HashMap<Integer, String> medicineList = new HashMap<Integer, String>();
		Iterable<Medicine> medicine = medicineRepository.findAll();
		for (Medicine m : medicine) {
			medicineList.put(m.getId(), m.getName());
		}
		return medicineList;
	}
}
