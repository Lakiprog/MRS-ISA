package com.MRSISA2021_T15.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.model.Supplier;
import com.MRSISA2021_T15.repository.UserRepository;

@Service
public class SupplierServiceImpl implements SupplierService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Boolean updateSupplierData(Supplier supplier) {
		Boolean passwordChanged = false;
		Supplier updatedSupplier = (Supplier) userRepository.findByUsername(supplier.getUsername());
		updatedSupplier.setName(supplier.getName());
		updatedSupplier.setSurname(supplier.getSurname());
		updatedSupplier.setAdress(supplier.getAdress());
		updatedSupplier.setCity(supplier.getCity());
		updatedSupplier.setCountry(supplier.getCountry());
		updatedSupplier.setPhoneNumber(supplier.getPhoneNumber());
		if (supplier.getPassword() != null) {
			updatedSupplier.setPassword(passwordEncoder.encode(supplier.getPassword()));
			passwordChanged = true;
		}
		userRepository.save(updatedSupplier);
		return passwordChanged;
	}

	@Override
	public Supplier getSupplierData() {
		return (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}