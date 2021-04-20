package com.MRSISA2021_T15.service;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.dto.UserTokenState;
import com.MRSISA2021_T15.model.Supplier;
import com.MRSISA2021_T15.model.User;
import com.MRSISA2021_T15.repository.UserRepository;
import com.MRSISA2021_T15.utils.TokenUtils;

@Service
public class SupplierServiceImpl implements SupplierService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Override
	public ResponseEntity<UserTokenState> updateSupplierData(Supplier supplier) {
		Supplier updatedSupplier = (Supplier) userRepository.findByUsername(supplier.getUsername());
		updatedSupplier.setPassword(passwordEncoder.encode(supplier.getPassword()));
		updatedSupplier.setName(supplier.getName());
		updatedSupplier.setSurname(supplier.getSurname());
		updatedSupplier.setAdress(supplier.getAdress());
		updatedSupplier.setCity(supplier.getCity());
		updatedSupplier.setCountry(supplier.getCountry());
		updatedSupplier.setPhoneNumber(supplier.getPhoneNumber());
		userRepository.save(updatedSupplier);
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				supplier.getUsername(), supplier.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		User user = (User) authentication.getPrincipal();
		String jwt = tokenUtils.generateToken(user.getUsername());
		int expiresIn = tokenUtils.getExpiredIn();
		return ResponseEntity.ok(new UserTokenState(jwt, expiresIn, user.getId(), user.getUsername(), user.getRoles().get(0).getName()));
	}

	@Override
	public Supplier getSupplierData(String username) {
		return (Supplier) userRepository.findByUsername(username);
	}

}
