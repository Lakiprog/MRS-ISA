package com.MRSISA2021_T15.service;

import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String updateSupplierData(Supplier supplier) {
		String message = "";
		Supplier currentUser = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (currentUser != null) {
			currentUser.setName(supplier.getName());
			currentUser.setSurname(supplier.getSurname());
			currentUser.setAddress(supplier.getAddress());
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

	@Override
	public List<MedicineSupply> getMedicineSupply() {
		Supplier supplier = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return medicineSupplyRepository.findAllBySupplierId(supplier.getId());
	}
	
	@Override
	public List<String> getOrders() {
		return purchaseOrderRepository.findOrdersByDueDateAfterCurrentDate(LocalDate.now());
	}
	
	@Override
	public String writeOffer(PurchaseOrderSupplier offer) {
		String message = "";
		Supplier supplier = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PurchaseOrder order = purchaseOrderRepository.findByOrderName(offer.getOrderName());
		PurchaseOrderSupplier pos = purchaseOrderSupplierRepository.findBySupplierIdAndPurchaseOrderId(order.getId(), supplier.getId());
		if (pos == null) {
			List<MedicineSupply> ms = medicineSupplyRepository.hasNoMedicineInStock(order.getId(), supplier.getId());
			if (ms.isEmpty()) {
				offer.setPurchaseOrder(order);
				offer.setOfferStatus(OfferStatus.PENDING);
				offer.setSupplier(supplier);
				purchaseOrderSupplierRepository.save(offer);
			} else {
				message = "You do not have enough of medicine in stock!";
			}	
		} else {
			message = "Supplier has already given an offer for this order!";
		}
		return message;
	}

	@Override
	public List<PurchaseOrderMedicine> getOrderByName(String orderName) {
		return purchaseOrderMedicineRepository.findAllByOrderName(orderName);
	}

	@Override
	public List<PurchaseOrderSupplier> getOffers() {
		return purchaseOrderSupplierRepository.findBySupplier((Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
	}
	
	
}