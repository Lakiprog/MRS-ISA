package com.MRSISA2021_T15.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.MRSISA2021_T15.model.Supplier;
import com.MRSISA2021_T15.service.SupplierService;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {
	
	@Autowired
	private SupplierService supplierService;
	
	@PutMapping(value = "/updateSupplierData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SUPPLIER')")
	public @ResponseBody Boolean updateSupplierData(@RequestBody Supplier supplier) {
		return supplierService.updateSupplierData(supplier);
		
	}
	
	@GetMapping(value = "/getSupplierData", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SUPPLIER')")
	public Supplier getSupplierData() {
		return supplierService.getSupplierData();
	}

}
