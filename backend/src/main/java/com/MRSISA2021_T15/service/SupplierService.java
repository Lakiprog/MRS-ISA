package com.MRSISA2021_T15.service;

import org.springframework.http.ResponseEntity;

import com.MRSISA2021_T15.dto.UserTokenState;
import com.MRSISA2021_T15.model.Supplier;

public interface SupplierService {
	
	ResponseEntity<UserTokenState> updateSupplierData(Supplier supplier);
	
	Supplier getSupplierData(String username);

}
