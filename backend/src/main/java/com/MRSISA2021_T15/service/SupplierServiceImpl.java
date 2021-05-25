package com.MRSISA2021_T15.service;

import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.dto.ChangePassword;
import com.MRSISA2021_T15.model.MedicineSupply;
import com.MRSISA2021_T15.model.OfferStatus;
import com.MRSISA2021_T15.model.PurchaseOrder;
import com.MRSISA2021_T15.model.PurchaseOrderMedicine;
import com.MRSISA2021_T15.model.PurchaseOrderSupplier;
import com.MRSISA2021_T15.model.Supplier;
import com.MRSISA2021_T15.repository.MedicineSupplyRepository;
import com.MRSISA2021_T15.repository.PurchaseOrderMedicineRepository;
import com.MRSISA2021_T15.repository.PurchaseOrderRepository;
import com.MRSISA2021_T15.repository.PurchaseOrderSupplierRepository;
import com.MRSISA2021_T15.repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class SupplierServiceImpl implements SupplierService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MedicineSupplyRepository medicineSupplyRepository;
	
	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired
	private PurchaseOrderMedicineRepository purchaseOrderMedicineRepository;
	
	@Autowired
	private PurchaseOrderSupplierRepository purchaseOrderSupplierRepository;
	
	@Override
	public void updateSupplierData(Supplier supplier) {
		Supplier currentUser = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!supplier.getName().equals("")) {
			currentUser.setName(supplier.getName());
		}
		if (!supplier.getSurname().equals("")) {
			currentUser.setSurname(supplier.getSurname());
		}
		if (!supplier.getAddress().equals("")) {
			currentUser.setAddress(supplier.getAddress());
		}
		if (!supplier.getCity().equals("")) {
			currentUser.setCity(supplier.getCity());
		}
		if (!supplier.getCountry().equals("")) {
			currentUser.setCountry(supplier.getCountry());
		}
		if (!supplier.getPhoneNumber().equals("")) {
			currentUser.setPhoneNumber(supplier.getPhoneNumber());
		}
		userRepository.save(currentUser);
	}
	
	@Override
	public String updatePassword(ChangePassword passwords) {
		String message = "";
		Supplier currentUser = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!passwordEncoder.matches(passwords.getOldPassword(), currentUser.getPassword())) {
			message = "Wrong old password!";
		} else {
			if (currentUser.getFirstLogin()) {
				currentUser.setFirstLogin(false);
			}
			currentUser.setPassword(passwordEncoder.encode(passwords.getPassword()));
			userRepository.save(currentUser);
		}
		return message;
	}

	@Override
	public Supplier getSupplierData() {
		return (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	@Override
	public List<MedicineSupply> getMedicineSupply() {
		Supplier supplier = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return medicineSupplyRepository.findAllBySupplierId(supplier.getId());
	}
	
	@Override
	public List<PurchaseOrder> getOrders() {
		return purchaseOrderRepository.findOrdersByDueDateAfterCurrentDate(LocalDate.now());
	}
	
	@Override
	public String writeOffer(PurchaseOrderSupplier offer) {
		String message = "";
		Supplier supplier = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (supplier.getFirstLogin()) {
			message =  "You are logging in for the first time, you must change password before you can use this functionality!";
		} else if (offer.getPurchaseOrder().getDueDateOffer().isBefore(LocalDate.now())) {
			message = "Due date must be before today's date!";
		} else if (LocalDate.now().isAfter(offer.getDeliveryDate())) {
			message = "Delivery date must be after today's date!";
		} else {
			PurchaseOrderSupplier pos = purchaseOrderSupplierRepository.findBySupplierIdAndPurchaseOrderId(offer.getPurchaseOrder().getId(), supplier.getId());
			if (pos == null) {
				List<MedicineSupply> ms = medicineSupplyRepository.hasNoMedicineInStock(offer.getPurchaseOrder().getId(), supplier.getId());
				if (ms.isEmpty()) {
					offer.setOfferStatus(OfferStatus.PENDING);
					offer.setSupplier(supplier);
					offer.setPrice(Math.abs(offer.getPrice()));
					purchaseOrderSupplierRepository.save(offer);
				} else {
					message = "You do not have enough of medicine in stock!";
				}	
			} else {
				message = "Supplier has already given an offer for this order!";
			}
		}
		return message;
	}

	@Override
	public List<PurchaseOrderMedicine> getPurchaseOrdersMedicine(PurchaseOrder purchaseOrder) {
		return purchaseOrderMedicineRepository.findAllByPurchaseOrder(purchaseOrder);
	}

	@Override
	public List<PurchaseOrderSupplier> getOffersBySupplier() {
		return purchaseOrderSupplierRepository.findBySupplier((Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
	}

	@Override
	public List<PurchaseOrderSupplier> getPendingOffersBySupplier() {
		return purchaseOrderSupplierRepository.getPendingOffers(((Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
	}

	@Override
	public String updateOffer(PurchaseOrderSupplier offer) {
		String message = "";
		Supplier supplier = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (supplier.getFirstLogin()) {
			message =  "You are logging in for the first time, you must change password before you can use this functionality!";
		} else if (LocalDate.now().isAfter(offer.getDeliveryDate())) {
			message = "Delivery date must be after today's date!";
		} else if (offer.getPurchaseOrder().getDueDateOffer().isBefore(LocalDate.now())) {
			message = "Due date must be before today's date!";
		} else {
			PurchaseOrderSupplier offerToUpdate = purchaseOrderSupplierRepository.findById(offer.getId()).get();
			if (offer.getPrice() != null) {
				offerToUpdate.setPrice(Math.abs(offer.getPrice()));
			}
			if (offer.getDeliveryDate() != null) {
				offerToUpdate.setDeliveryDate(offer.getDeliveryDate());
			}
			purchaseOrderSupplierRepository.save(offerToUpdate);
		}
		return message;
	}

	@Override
	public ResponseEntity<String> updateMedicineStock(MedicineSupply medicineSupply) {
		String message = "";
		Gson gson = new GsonBuilder().create();
		Supplier supplier = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (supplier.getFirstLogin()) {
			message = "You are logging in for the first time, you must change password before you can use this functionality!";
			return new ResponseEntity<String>(gson.toJson(message), HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
		MedicineSupply ms = medicineSupplyRepository.getMedicineSupplyBySupplier(medicineSupply.getMedicine().getMedicineCode(), supplier.getId());
		if (ms != null) {
			ms.setQuantity(Math.abs(medicineSupply.getQuantity()));
			medicineSupplyRepository.save(ms);
			message = "Medicine stock updated.";
		} else {
			medicineSupply.setSupplier(supplier);
			medicineSupply.setQuantity(Math.abs(medicineSupply.getQuantity()));
			medicineSupplyRepository.save(medicineSupply);
			message = "Medicine is added to stock.";
		}
		}
		return new ResponseEntity<String>(gson.toJson(message), HttpStatus.OK);
	}
}