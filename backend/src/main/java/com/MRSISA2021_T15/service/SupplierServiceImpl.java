package com.MRSISA2021_T15.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.model.Supplier;
import com.MRSISA2021_T15.repository.UserRepository;

@Service
public class SupplierServiceImpl implements SupplierService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public String updateSupplierData(Supplier supplier) {
		String message = "";
		if (userRepository.findByEmail(supplier.getEmail()) != null && 
				!userRepository.findByEmail(supplier.getEmail()).getUsername().equals(supplier.getUsername())) {
			message = "A user with this email already exists!";
		} else if (userRepository.findByUsername(supplier.getUsername()) == null) {
			message = "User: " + supplier.getUsername() + " does not exist!";
		} else {
			Supplier updatedSupplier = (Supplier) userRepository.findByUsername(supplier.getUsername());
			updatedSupplier.setEmail(supplier.getEmail());
			updatedSupplier.setPassword(supplier.getPassword());
			updatedSupplier.setName(supplier.getName());
			updatedSupplier.setSurname(supplier.getSurname());
			updatedSupplier.setAdress(supplier.getAdress());
			updatedSupplier.setCity(supplier.getCity());
			updatedSupplier.setCountry(supplier.getCountry());
			updatedSupplier.setPhoneNumber(supplier.getPhoneNumber());
			userRepository.save(updatedSupplier);
		}
		return message;
	}

	@Override
	public Supplier getSupplierData(String username) {
		return (Supplier) userRepository.findByUsername(username);
	}

}
