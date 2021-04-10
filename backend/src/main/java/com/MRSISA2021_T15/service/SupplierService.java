package com.MRSISA2021_T15.service;

import com.MRSISA2021_T15.model.Supplier;

public interface SupplierService {
	
	String updateSupplierData(Supplier supplier);
	
	Supplier getSupplierData(String username);

}
