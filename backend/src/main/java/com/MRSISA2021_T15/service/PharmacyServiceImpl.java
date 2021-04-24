package com.MRSISA2021_T15.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MRSISA2021_T15.model.Pharmacy;
import com.MRSISA2021_T15.model.PharmacyAdmin;
import com.MRSISA2021_T15.repository.PharmacyAdminRepository;
import com.MRSISA2021_T15.repository.PharmacyRepository;

@Service
public class PharmacyServiceImpl implements PharmacyService {
	
	@Autowired
	private PharmacyRepository pharmacyRepository;

	@Autowired
	private PharmacyAdminRepository pharmacyAdminRepository;
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public String registerPharmacy(Pharmacy pharmacy) {
		String message = "";
		List<Integer> pharmacyAdministratorsIds = (List<Integer>) pharmacy.getPharmacyAdminsIds().clone();
		pharmacyRepository.save(pharmacy);
		if (pharmacyAdministratorsIds != null) {
			for (Integer id : pharmacyAdministratorsIds) {
				PharmacyAdmin pharmacyAdmin = pharmacyAdminRepository.findById(id).get();
				if (pharmacyAdmin != null) {
					pharmacyAdmin.setPharmacy(pharmacy);
					pharmacyAdminRepository.save(pharmacyAdmin);
				}
			}
		} else {
			message = "The pharmacy has not been registered!";
		}
		return message;
	}

	@Override
	public HashMap<Integer, String> getPharmacyAdminsWithNoPharmacy() {
		HashMap<Integer, String> pharmacyAdminsWithNoPharmacy = new HashMap<Integer, String>();
		Iterable<PharmacyAdmin> pharmacyAdmins = pharmacyAdminRepository.findAll();
		for (PharmacyAdmin pa : pharmacyAdmins) {
			if (pa.getPharmacy() == null) {
				pharmacyAdminsWithNoPharmacy.put(pa.getId(), pa.getUsername());
			}
		}
		return pharmacyAdminsWithNoPharmacy;
	}

}
