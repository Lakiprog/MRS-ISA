package com.MRSISA2021_T15.service;

import com.MRSISA2021_T15.dto.ChangePassword;
import com.MRSISA2021_T15.model.Supplier;

public interface SupplierService {
	
	String updateSupplierData(Supplier supplier);
	
	String updatePassword(ChangePassword passwords);
	
	Supplier getSupplierData();

}
