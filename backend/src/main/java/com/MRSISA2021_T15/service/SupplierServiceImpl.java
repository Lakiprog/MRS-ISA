package com.MRSISA2021_T15.service;

import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

	@Override
	public List<MedicineSupply> getMedicineSupply() {
		Supplier supplier = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return medicineSupplyRepository.findAllBySupplierId(supplier.getId());
	}
	
	@Override
	public List<String> getOrders() {
		List<String> orderNames = new ArrayList<String>();
		Iterable<PurchaseOrder> orders = purchaseOrderRepository.findAll();
		for (PurchaseOrder po : orders) {
			if (po.getDueDateOffer().isAfter(LocalDateTime.now())) {
				orderNames.add(po.getOrderName());
			}
		}
		return orderNames;
	}
	
	

	@Override
	public String writeOffer(PurchaseOrderSupplier offer) {
		Supplier supplier = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PurchaseOrder order = purchaseOrderRepository.findByOrderName(offer.getOrderName());
		offer.setPurchaseOrder(order);
		offer.setOfferStatus(OfferStatus.PENDING);
		offer.setSupplier(supplier);
		String message = "";
		PurchaseOrderSupplier pos = purchaseOrderSupplierRepository.findBySupplierIdAndPurchaseOrderId(order.getId(), supplier.getId());
		if (pos == null) {
			boolean hasMedicine = true;
			List<PurchaseOrderMedicine> orders = purchaseOrderMedicineRepository.findAllByOrderName(offer.getOrderName());
			List<MedicineSupply> medicineSupply = getMedicineSupply();
			for (PurchaseOrderMedicine pom : orders) {
				for (MedicineSupply ms : medicineSupply) {
					if (pom.getMedicine().getMedicineCode().equals(ms.getMedicine().getMedicineCode())) {
						if (ms.getQuantity() < pom.getQuantity()) {
							hasMedicine = false;
							message = "You do not have enough of medicine " + ms.getMedicine().getMedicineCode() + " in stock!";
							break;
						}
					}
				}
			}
			if (hasMedicine) {
				purchaseOrderSupplierRepository.save(offer);
				
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
}