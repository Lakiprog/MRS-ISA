package com.MRSISA2021_T15.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.MRSISA2021_T15.repository.CategoryRepository;
import com.MRSISA2021_T15.repository.EReceiptAndMedicineDetailsRepository;
import com.MRSISA2021_T15.repository.EReceiptMedicineDetailsRepository;
import com.MRSISA2021_T15.repository.EReceiptRepository;
import com.MRSISA2021_T15.repository.MedicinePharmacyRepository;
import com.MRSISA2021_T15.repository.MedicineRepository;
import com.MRSISA2021_T15.repository.PatientSubPharmacyRepository;
import com.MRSISA2021_T15.repository.PharmacyRepository;
import com.MRSISA2021_T15.repository.PromotionRepository;
import com.MRSISA2021_T15.repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.MRSISA2021_T15.dto.ChangePassword;
import com.MRSISA2021_T15.model.Category;
import com.MRSISA2021_T15.model.CategoryName;
import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.EReceipt;
import com.MRSISA2021_T15.model.EReceiptAndMedicineDetails;
import com.MRSISA2021_T15.model.EReceiptMedicineDetails;
import com.MRSISA2021_T15.model.EReceiptSearch;
import com.MRSISA2021_T15.model.MedicinePharmacy;
import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.PatientSubPharmacy;
import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.model.Pharmacy;

@Service
public class PatientService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private PatientSubPharmacyRepository patientSubPharmacyRepository;
	
	@Autowired
	private PromotionRepository promotionRepository;
	
	@Autowired
    private EmailSenderService emailSenderService;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private MedicinePharmacyRepository medicinePharmacyRepository;
	
	@Autowired
	private PharmacyRepository pharmacyRepository;
	
	@Autowired
	private EReceiptRepository eReceiptRepository;
	
	@Autowired
	private EReceiptMedicineDetailsRepository eReceiptMedicineDetailsRepository;
	
	@Autowired
	private EReceiptAndMedicineDetailsRepository eReceiptAndMedicineDetailsRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private MedicineRepository medicineRepository;
	
	public List<Patient> findAllPatients(){
		return repository.findAllPatients();
	}
	
	public List<Dermatologist> findAllDermatologist(){
		return repository.findAllDermatologist();
	}
	
	public Dermatologist findDermatologistWithId(Integer id) {
		return repository.findDermatologistWithId(id);
	}
	
	public Pharmacist findPharmacistWithId(Integer id) {
		return repository.findPharmacistWithId(id);
	}
	
	
	public List<Pharmacist>findAllPharmacist(){
		return repository.findAllPharmacist();
	}
	
	public String updatePatientData(Patient p) {
		String message = "";
		Patient currentUser = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (currentUser != null) {
			currentUser.setName(p.getName());
			currentUser.setSurname(p.getSurname());
			currentUser.setAddress(p.getAddress());
			currentUser.setCity(p.getCity());
			currentUser.setCountry(p.getCountry());
			currentUser.setPhoneNumber(p.getPhoneNumber());
			repository.save(currentUser);
		} else {
			message = "Update unsuccessfull!";
		}
		return message;
	}
	
	
	public String updatePassword(ChangePassword passwords) {
		String message = "";
		Patient currentUser = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (currentUser != null) {
			if (!passwordEncoder.matches(passwords.getOldPassword(), currentUser.getPassword())) {
				message = "Wrong old password!";
			} else {
				currentUser.setPassword(passwordEncoder.encode(passwords.getPassword()));
				repository.save(currentUser);
			}
		} else {
			message = "Password update unsuccessfull!";
		}
		return message;
	}
	
	
	public Patient getPatientData() {
		return (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	public void subscribeToPharamacy(@RequestBody Pharmacy pharmacy) {
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PatientSubPharmacy patientSubPharmacy = patientSubPharmacyRepository.findByPharamcyByPharmacyIdAndPatientId(pharmacy.getId(), patient.getId());
		if (patientSubPharmacy != null) {
			patientSubPharmacy.setSubscribed(true);
			patientSubPharmacyRepository.save(patientSubPharmacy);
		} else {
			PatientSubPharmacy psp = new PatientSubPharmacy();
			psp.setPatient(patient);
			psp.setPharmacy(pharmacy);
			psp.setSubscribed(true);
			patientSubPharmacyRepository.save(psp);
		}
	}
	
	public void unsubscribeToPharamacy(@RequestBody Pharmacy pharmacy) {
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PatientSubPharmacy patientSubPharmacy = patientSubPharmacyRepository.findByPharamcyByPharmacyIdAndPatientId(pharmacy.getId(), patient.getId());
		if (patientSubPharmacy != null) {
			patientSubPharmacy.setSubscribed(false);
			patientSubPharmacyRepository.save(patientSubPharmacy);
		}
	}
	
	public List<Pharmacy> getSubscribedPharmacies() {
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return patientSubPharmacyRepository.getSubscribedPharmaciesForPatient(patient.getId());
	}
	
	@Scheduled(fixedDelayString = "PT24H")
	public void sendPromotionMails() throws InterruptedException {
		List<PatientSubPharmacy> subscribedPatients = patientSubPharmacyRepository.findBySubscribedTrue();
		for (PatientSubPharmacy psp: subscribedPatients) {
			List<String> descriptions = promotionRepository.getDescriptionByPharmacyId(psp.getPharmacy().getId(), LocalDate.now());
			for (String description : descriptions) {
				SimpleMailMessage mailMessage = new SimpleMailMessage();
	            mailMessage.setTo(psp.getPatient().getEmail());
	            mailMessage.setSubject("Pharmacy " + psp.getPharmacy().getName() + " PROMOTION!!!!");
	            mailMessage.setFrom(env.getProperty("spring.mail.username"));
	            mailMessage.setText(description);
	            emailSenderService.sendEmail(mailMessage);
	            Thread.sleep(3000);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<EReceiptSearch> sendQrCode(MultipartFile file) throws IOException, NotFoundException {
		InputStream is = new ByteArrayInputStream(file.getBytes());
        BufferedImage newBi = ImageIO.read(is);
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(newBi)));
		Result result = new MultiFormatReader().decode(binaryBitmap);
		String jsonString = result.getText();
		Gson gson = new Gson();
		List<EReceiptMedicineDetails> requiredMedicine = (List<EReceiptMedicineDetails>) gson.fromJson(jsonString, new TypeToken<List<EReceiptMedicineDetails>>(){}.getType());
		List<Pharmacy> pharmacies = (List<Pharmacy>) pharmacyRepository.findAll();
		List<EReceiptSearch> pharmaciesWithRequiredMedicine = new ArrayList<EReceiptSearch>();
		for (Pharmacy p : pharmacies) {
			List<MedicinePharmacy> tempList = new ArrayList<MedicinePharmacy>();
			for (EReceiptMedicineDetails ermd: requiredMedicine) {
				MedicinePharmacy pharmacyWithRequiredMedicine = medicinePharmacyRepository.getPharmacyByIdAndMedicineCode(p.getId(), ermd.getMedicineCode(), ermd.getQuantity());
				if (pharmacyWithRequiredMedicine != null) {
					tempList.add(pharmacyWithRequiredMedicine);
				}
			}
			if (tempList.size() == requiredMedicine.size()) {
				EReceiptSearch ers = new EReceiptSearch();
				ers.setPharmacy(p);
				ers.seteReceiptMedicineDetails(requiredMedicine);
				for (MedicinePharmacy mp : tempList) {
					for (EReceiptMedicineDetails ermd: requiredMedicine) {
						if (mp.getMedicine().getMedicineCode().equals(ermd.getMedicineCode())) {
							ers.setTotal(ers.getTotal() + (mp.getCost() * ermd.getQuantity()));
							break;
						}
					}
				}
				pharmaciesWithRequiredMedicine.add(ers);
			}
		}
		return pharmaciesWithRequiredMedicine;
	}

	public void issueEReceipt(EReceiptSearch eReceiptSearch) {
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EReceipt eReceipt = new EReceipt();
		eReceipt.seteReceiptCode(UUID.randomUUID().toString());
		eReceipt.setIssueDate(LocalDate.now());
		eReceipt.setPatient(patient);
		eReceipt = eReceiptRepository.save(eReceipt);
		for (EReceiptMedicineDetails ermd : eReceiptSearch.geteReceiptMedicineDetails()) {
			patient.setCollectedPoints(patient.getCollectedPoints() + (medicineRepository.getPointsByMedicineCode(ermd.getMedicineCode()) * ermd.getQuantity()));
			if (patient.getCategoryName().equals(CategoryName.REGULAR)) {
				Category c = categoryRepository.findByCategoryName(CategoryName.SILVER);
				if (patient.getCollectedPoints() >= c.getRequiredNumberOfPoints()) {
					patient.setCategoryName(CategoryName.SILVER);
				}
			} else if (patient.getCategoryName().equals(CategoryName.SILVER)) {
				Category c1 = categoryRepository.findByCategoryName(CategoryName.GOLD);
				if (patient.getCollectedPoints() >= c1.getRequiredNumberOfPoints()) {
					patient.setCategoryName(CategoryName.GOLD);
				}
			}
			ermd = eReceiptMedicineDetailsRepository.save(ermd);
			EReceiptAndMedicineDetails eramd = new EReceiptAndMedicineDetails();
			eramd.seteReceipt(eReceipt);
			eramd.seteReceiptMedicineDetails(ermd);
			eReceiptAndMedicineDetailsRepository.save(eramd);
		}
		repository.save(patient);
		SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(patient.getEmail());
        mailMessage.setSubject("Medicine issue via EReceipt from pharmacy " + eReceiptSearch.getPharmacy().getName());
        mailMessage.setFrom(env.getProperty("spring.mail.username"));
        String mailText = "You have been issued the following medication: \n";
        for (EReceiptMedicineDetails ermd : eReceiptSearch.geteReceiptMedicineDetails()) {
        	mailText += "\t" + ermd.getMedicineName() + ", Quantity: " + ermd.getQuantity() + "\n";
        }
        mailText += "\tTotal price: " + eReceiptSearch.getTotal() + "\n";
        mailText += "\nBest regards,\nPharmacy " + eReceiptSearch.getPharmacy().getName();
        mailMessage.setText(mailText);
        emailSenderService.sendEmail(mailMessage);
		List<MedicinePharmacy> toUpdatePharmacyStock = medicinePharmacyRepository.findByPharmacyId(eReceiptSearch.getPharmacy().getId());
		for (MedicinePharmacy mp : toUpdatePharmacyStock) {
			for (EReceiptMedicineDetails ermd : eReceiptSearch.geteReceiptMedicineDetails()) {
				if (mp.getMedicine().getMedicineCode().equals(ermd.getMedicineCode())) {
					mp.setAmount(mp.getAmount() - ermd.getQuantity());
					break;
				}
			}
		}
		medicinePharmacyRepository.saveAll(toUpdatePharmacyStock);
	}
}
