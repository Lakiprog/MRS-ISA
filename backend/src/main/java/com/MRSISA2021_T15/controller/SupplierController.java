package com.MRSISA2021_T15.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MRSISA2021_T15.dto.ChangePassword;
import com.MRSISA2021_T15.model.MedicineSupply;
import com.MRSISA2021_T15.model.PurchaseOrderMedicine;
import com.MRSISA2021_T15.model.PurchaseOrderSupplier;
import com.MRSISA2021_T15.model.Supplier;
import com.MRSISA2021_T15.service.SupplierService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {
	
	@Autowired
	private SupplierService supplierService;
	
	@PutMapping(value = "/updateSupplierData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SUPPLIER')")
	public ResponseEntity<String> updateSupplierData(@RequestBody Supplier supplier) {
		String message = supplierService.updateSupplierData(supplier);
		Gson gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<String>(gson.toJson("Update successfull."), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "/updatePassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SUPPLIER')")
	public ResponseEntity<String> updatePassword(@RequestBody ChangePassword passwords) {
		String message = supplierService.updatePassword(passwords);
		Gson gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<String>(gson.toJson(""), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/getSupplierData", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SUPPLIER')")
	public Supplier getSupplierData() {
		return supplierService.getSupplierData();
	}
	
	@GetMapping(value = "/getMedicineSupply", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SUPPLIER')")
	public List<MedicineSupply> getMedicineSupply() {
		return supplierService.getMedicineSupply();
	}
	
	@PutMapping(value = "/writeOffer", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SUPPLIER')")
	public ResponseEntity<String> writeOffer(@RequestBody PurchaseOrderSupplier offer) {
		String message = supplierService.writeOffer(offer);
		Gson gson = new GsonBuilder().create();
		if (message.equals("")) {
			return new ResponseEntity<String>(gson.toJson("Offer sent successfully."), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/getOrders", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SUPPLIER')")
	public List<String> getOrders() {
		return supplierService.getOrders();
	}
	
	@GetMapping(value = "/getOrderByName/{orderName}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_SUPPLIER')")
	public List<PurchaseOrderMedicine> getOrderByName(@PathVariable String orderName) {
		return supplierService.getOrderByName(orderName);
	}
}
