package com.MRSISA2021_T15.controller;

import com.MRSISA2021_T15.model.*;
import com.MRSISA2021_T15.repository.*;
import com.MRSISA2021_T15.service.EmailSenderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/purchaseOrder")
public class PurchaseOrderController {

    @Autowired
    public PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    public PurchaseOrderMedicineRepository purchaseOrderMedicineRepository;
    @Autowired
    public PurchaseOrderSupplierRepository purchaseOrderSupplierRepository;
    @Autowired
    public MedicinePharmacyRepository medicinePharmacyRepository;
    @Autowired
    public PharmacyRepository pharmacyRepository;
    @Autowired
    EmailSenderService emailSenderIvan3;
    @Autowired
    Environment environment;

    @RequestMapping(value = "/activePurchaseOrders/{pharmacyId}")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public ArrayList<PurchaseOrder> getActivePurchaseOrders(@PathVariable Integer pharmacyId){
        ArrayList<PurchaseOrder> returnList = new ArrayList<>();

        for(PurchaseOrder po: purchaseOrderRepository.findAll()){
            if(po.getPharmacy().getId()==pharmacyId &&
                    (LocalDate.now().isBefore(po.getDueDateOffer()) || LocalDate.now().isEqual(po.getDueDateOffer()))){
                returnList.add(po);
            }
        }

        return returnList;
    }



    @RequestMapping(value = "/{purchaseOrderId}/getPurchaseOrder")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public ArrayList<PurchaseOrderMedicine> getPurchaseOrder(@PathVariable Integer purchaseOrderId){
        ArrayList<PurchaseOrderMedicine> returnList = new ArrayList<>();
        for(PurchaseOrderMedicine pom: purchaseOrderMedicineRepository.findAll())
            if(pom.getPurchaseOrder().getId() == purchaseOrderId)
                returnList.add(pom);
        return returnList;
    }

    @RequestMapping(value = "/getPendingPurchaseOrderSupplier/{pharmacyId}")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public ArrayList<PurchaseOrderSupplier> getPendingPurchaseOrderSupplier(@PathVariable Integer pharmacyId){
        ArrayList<PurchaseOrderSupplier> returnList = new ArrayList<>();
        for(PurchaseOrderSupplier pos: purchaseOrderSupplierRepository.findAll()){
            if((LocalDate.now().isBefore(pos.getDeliveryDate()) || LocalDate.now().isEqual(pos.getDeliveryDate())) && pos.getOfferStatus().equals(OfferStatus.PENDING)){
                if(pos.getPurchaseOrder().getPharmacy().getId() == pharmacyId) {
                    returnList.add(pos);
                }
            }
        }
        return returnList;
    }

    @RequestMapping(value = "/getAllPurchaseOrderSupplier/{pharmacyId}")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public ArrayList<PurchaseOrderSupplier> getAllPurchaseOrderSupplier(@PathVariable Integer pharmacyId){
        ArrayList<PurchaseOrderSupplier> returnList = new ArrayList<>();
        for(PurchaseOrderSupplier pos: purchaseOrderSupplierRepository.findAll()){
                if(pos.getPurchaseOrder().getPharmacy().getId() == pharmacyId) {
                    returnList.add(pos);
                }

        }
        return returnList;
    }

    @PutMapping(value = "/acceptPurchaseOrderSupplier/{pharmacyAdminId}")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public ResponseEntity<String> acceptPurchaseOrderSupplier(@RequestBody PurchaseOrderSupplier pos, @PathVariable Integer pharmacyAdminId){
        Iterable<PurchaseOrderSupplier> allPurchaseOrders = purchaseOrderSupplierRepository.findAll();
        List<MedicinePharmacy> medicinePharmacyList = medicinePharmacyRepository.findByPharmacyId(pos.getPurchaseOrder().getPharmacy().getId());
        List<PurchaseOrderMedicine> purchaseOrderMedicineList = purchaseOrderMedicineRepository.findAllByPurchaseOrder(pos.getPurchaseOrder());
        //check if the pharmacy admin accepting the offer is the one who created the purchase order
        if(pos.getPurchaseOrder().getPharmacyAdmin().getId() == pharmacyAdminId) {
            //accept purchase order offer
            pos.setOfferStatus(OfferStatus.ACCEPTED);
            //reject purchase order offers from other suppliers
            for(PurchaseOrderSupplier purchaseOrderSupplier : allPurchaseOrders){
                if(purchaseOrderSupplier.getPurchaseOrder().getId() == pos.getPurchaseOrder().getId() &&
                    purchaseOrderSupplier.getId() != pos.getId()) {
                    rejectPurchaseOrderSupplier(purchaseOrderSupplier, pharmacyAdminId);
                }
                //supplier se izgubi verovatno zbog @JsonIgnore anotacije
                else{
                    pos.setSupplier(purchaseOrderSupplier.getSupplier());
                }
            }
            //update pharmacy's medicine quantity
            for( PurchaseOrderMedicine pom : purchaseOrderMedicineList)
                for (MedicinePharmacy mp : medicinePharmacyList){
                    if (pom.getMedicine().getId() == mp.getMedicine().getId()){
                        mp.setAmount(mp.getAmount() + pom.getQuantity());
                        medicinePharmacyRepository.save(mp);
                    }
                }
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(pos.getSupplier().getEmail());
            mailMessage.setSubject("Purchase order accepted");
            mailMessage.setFrom(environment.getProperty("spring.mail.username"));
            mailMessage.setText("Purchase order with name: " + pos.getPurchaseOrder().getPurchaseOrderName()
                    + " is accepted.");
            emailSenderIvan3.sendEmail(mailMessage);
            purchaseOrderSupplierRepository.save(pos);
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/rejectPurchaseOrderSupplier/{pharmacyAdminId}")
    @PreAuthorize("hasRole('ROLE_PHARMACY_ADMIN')")
    public ResponseEntity<String> rejectPurchaseOrderSupplier(@RequestBody PurchaseOrderSupplier pos, @PathVariable Integer pharmacyAdminId){
        Iterable<PurchaseOrderSupplier> allPurchaseOrders = purchaseOrderSupplierRepository.findAll();
        //supplier se izgubi verovatno zbog @JsonIgnore anotacije
        for(PurchaseOrderSupplier purchaseOrderSupplier : allPurchaseOrders) {
            if(purchaseOrderSupplier.getId() == pos.getId()){
                pos.setSupplier(purchaseOrderSupplier.getSupplier());
            }
        }
        if(pos.getPurchaseOrder().getPharmacyAdmin().getId() == pharmacyAdminId) {
            pos.setOfferStatus(OfferStatus.REJECTED);
            pos.getId();
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(pos.getSupplier().getEmail());
            mailMessage.setSubject("Purchase order rejected");
            mailMessage.setFrom(environment.getProperty("spring.mail.username"));
            mailMessage.setText("Purchase order with name: " + pos.getPurchaseOrder().getPurchaseOrderName()
                    + " is rejected.");
            emailSenderIvan3.sendEmail(mailMessage);
            purchaseOrderSupplierRepository.save(pos);
        }
        return ResponseEntity.ok().build();
    }



}
