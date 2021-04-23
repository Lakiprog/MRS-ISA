package com.MRSISA2021_T15.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.dto.ChangePassword;
import com.MRSISA2021_T15.model.Supplier;
import com.MRSISA2021_T15.repository.UserRepository;

@Service
public class SupplierServiceImpl implements SupplierService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public String updateSupplierData(Supplier supplier) {
		String message = "";
		Supplier currentUser = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (currentUser != null) {
			currentUser.setName(supplier.getName());
			currentUser.setSurname(supplier.getSurname());
			currentUser.setAdress(supplier.getAdress());
			currentUser.setCity(supplier.getCity());
			currentUser.setCountry(supplier.getCountry());
			currentUser.setPhoneNumber(supplier.getPhoneNumber());
			userRepository.save(currentUser);
		} else {
			message = "Update unsuccessfull!";
		}
		return message;
	}
	
	@Override
	public String updatePassword(ChangePassword passwords) {
		String message = "";
		Supplier currentUser = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (currentUser != null) {
			if (!passwordEncoder.matches(passwords.getOldPassword(), currentUser.getPassword())) {
				message = "Wrong old password!";
			} else {
				currentUser.setPassword(passwordEncoder.encode(passwords.getPassword()));
				userRepository.save(currentUser);
			}
		} else {
			message = "Password update unsuccessfull!";
		}
		return message;
	}

	@Override
	public Supplier getSupplierData() {
		return (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}