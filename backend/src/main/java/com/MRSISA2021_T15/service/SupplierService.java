package com.MRSISA2021_T15.service;

import java.util.List;

import com.MRSISA2021_T15.dto.ChangePassword;
import com.MRSISA2021_T15.model.MedicineSupply;
import com.MRSISA2021_T15.model.PurchaseOrderMedicine;
import com.MRSISA2021_T15.model.PurchaseOrderSupplier;
import com.MRSISA2021_T15.model.Supplier;

public interface SupplierService {
	
	String updateSupplierData(Supplier supplier);
	
	String updatePassword(ChangePassword passwords);
	
	Supplier getSupplierData();
	
	List<MedicineSupply> getMedicineSupply();
	
	List<String> getOrders();
	
	List<PurchaseOrderMedicine> getOrderByName(String orderName);
	
	String writeOffer(PurchaseOrderSupplier offer);

}
