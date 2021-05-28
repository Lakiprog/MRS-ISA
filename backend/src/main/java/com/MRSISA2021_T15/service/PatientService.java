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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
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
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public String subscribeToPharamacy(Pharmacy pharmacy) {
		String message = "";
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Patient patientDb = (Patient) repository.findById(patient.getId()).get();
		if (!patientDb.isEnabled()) {
			message = "You have not verified your account!";
		} else {
			PatientSubPharmacy patientSubPharmacy = patientSubPharmacyRepository.findByPharmacyIdAndPatientId(pharmacy.getId(), patient.getId());
			if (patientSubPharmacy != null) {
				patientSubPharmacy.setSubscribed(true);
				patientSubPharmacyRepository.save(patientSubPharmacy);
			} else {
				PatientSubPharmacy psp = new PatientSubPharmacy();
				psp.setPatient(patientDb);
				psp.setPharmacy(pharmacy);
				psp.setSubscribed(true);
				patientSubPharmacyRepository.save(psp);
			}
		}
		return message;
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public String unsubscribeToPharamacy(Pharmacy pharmacy) {
		String message = "";
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Patient patientDb = (Patient) repository.findById(patient.getId()).get();
		if (!patientDb.isEnabled()) {
			message = "You have not verified your account!";
		} else {
			PatientSubPharmacy patientSubPharmacy = patientSubPharmacyRepository.findByPharmacyIdAndPatientId(pharmacy.getId(), patient.getId());
			if (patientSubPharmacy != null) {
				patientSubPharmacy.setSubscribed(false);
				patientSubPharmacyRepository.save(patientSubPharmacy);
			}
		}
		return message;
	}
	
	@Transactional(readOnly = true)
	public List<Pharmacy> getSubscribedPharmacies() {
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return patientSubPharmacyRepository.getSubscribedPharmaciesForPatient(patient.getId());
	}
	
	@Scheduled(fixedDelayString = "PT24H")
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
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
	
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
	@SuppressWarnings("unchecked")
	public List<EReceiptSearch> sendQrCode(MultipartFile file) throws IOException, NotFoundException {
		InputStream is = new ByteArrayInputStream(file.getBytes());
        BufferedImage newBi = ImageIO.read(is);
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(newBi)));
		Result result = new MultiFormatReader().decode(binaryBitmap);
		String jsonString = result.getText();
		Gson gson = new Gson();
		List<EReceiptMedicineDetails> requiredMedicine = 
				(List<EReceiptMedicineDetails>) gson.fromJson(jsonString, new TypeToken<List<EReceiptMedicineDetails>>(){}.getType());
		List<Pharmacy> pharmacies = (List<Pharmacy>) pharmacyRepository.findAll();
		List<EReceiptSearch> pharmaciesWithRequiredMedicine = new ArrayList<EReceiptSearch>();
		for (Pharmacy p : pharmacies) {
			List<MedicinePharmacy> tempList = new ArrayList<MedicinePharmacy>();
			for (EReceiptMedicineDetails ermd: requiredMedicine) {
				MedicinePharmacy pharmacyWithRequiredMedicine = medicinePharmacyRepository.getPharmacyByIdAndMedicineCode(p.getId(), 
						ermd.getMedicineCode(), Math.abs(ermd.getQuantity()));
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
							ers.setTotal(Math.abs(ers.getTotal()) + (Math.abs(mp.getCost()) * Math.abs(ermd.getQuantity())));
							break;
						}
					}
				}
				pharmaciesWithRequiredMedicine.add(ers);
			}
		}
		return pharmaciesWithRequiredMedicine;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	public String issueEReceipt(EReceiptSearch eReceiptSearch) {
		String message = "";
		List<String> medicineCodes = new ArrayList<String>();
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Patient patientDb = (Patient) repository.findById(patient.getId()).get();
		if (!patientDb.isEnabled()) {
			message = "You have not verified your account!";
		} else {
			for (EReceiptMedicineDetails ermd : eReceiptSearch.geteReceiptMedicineDetails()) {
				MedicinePharmacy pharmacyWithRequiredMedicine = medicinePharmacyRepository.getPharmacyByIdAndMedicineCodePessimisticRead
				(
						eReceiptSearch.getPharmacy().getId(), 
						ermd.getMedicineCode(), 
						Math.abs(ermd.getQuantity())
				);
				if (pharmacyWithRequiredMedicine == null) {
					return "The pharmacy does not have the required medicine!";
				}
				medicineCodes.add(ermd.getMedicineCode());
			}
			EReceipt eReceipt = new EReceipt();
			eReceipt.seteReceiptCode(UUID.randomUUID().toString());
			eReceipt.setIssueDate(LocalDate.now());
			eReceipt.setPatient(patientDb);
			eReceipt.setTotal(eReceiptSearch.getTotal());
			eReceipt = eReceiptRepository.save(eReceipt);
			for (EReceiptMedicineDetails ermd : eReceiptSearch.geteReceiptMedicineDetails()) {
				patientDb.setCollectedPoints(Math.abs(patientDb.getCollectedPoints()) + 
						(medicineRepository.getPointsByMedicineCode(ermd.getMedicineCode()) * (Math.abs(ermd.getQuantity()))));
				ermd = eReceiptMedicineDetailsRepository.save(ermd);
				EReceiptAndMedicineDetails eramd = new EReceiptAndMedicineDetails();
				eramd.seteReceipt(eReceipt);
				eramd.seteReceiptMedicineDetails(ermd);
				eramd.setPharmacy(eReceiptSearch.getPharmacy());
				eReceiptAndMedicineDetailsRepository.save(eramd);
			}
			if (patientDb.getCategoryName().equals(CategoryName.REGULAR)) {
				Category c = categoryRepository.findByCategoryNamePessimisticWrite(CategoryName.SILVER);
				if (c != null) {
					if (Math.abs(patientDb.getCollectedPoints()) >= Math.abs(c.getRequiredNumberOfPoints())) {
						patientDb.setCategoryName(CategoryName.SILVER);
						eReceipt.setDiscount((100.0 - Math.abs(c.getDiscount())) / 100.0);
					} else {
						eReceipt.setDiscount(0.0);
					}
				}
			} else if (patientDb.getCategoryName().equals(CategoryName.SILVER)) {
				Category c1 = categoryRepository.findByCategoryNamePessimisticWrite(CategoryName.GOLD);
				Category c2 = categoryRepository.findByCategoryNamePessimisticWrite(CategoryName.SILVER);
				if (c1 != null && c2 != null) {
					if (Math.abs(patientDb.getCollectedPoints()) >= Math.abs(c1.getRequiredNumberOfPoints())) {
						patientDb.setCategoryName(CategoryName.GOLD);
						eReceipt.setDiscount((100.0 - Math.abs(c1.getDiscount())) / 100.0);
					} else {
						eReceipt.setDiscount((100.0 - Math.abs(c2.getDiscount())) / 100.0);
					}
				}
			} else if (patientDb.getCategoryName().equals(CategoryName.GOLD)) {
				Category c1 = categoryRepository.findByCategoryNamePessimisticWrite(CategoryName.GOLD);
				if (c1 != null) {
					eReceipt.setDiscount((100.0 - Math.abs(c1.getDiscount())) / 100.0);
				}
			}
			eReceiptRepository.save(eReceipt);
			repository.save(patientDb);
			List<MedicinePharmacy> toUpdatePharmacyStock = medicinePharmacyRepository.findByPharmacyIdPessimisticWrite(eReceiptSearch.getPharmacy().getId(), medicineCodes);
			for (MedicinePharmacy mp : toUpdatePharmacyStock) {
				for (EReceiptMedicineDetails ermd : eReceiptSearch.geteReceiptMedicineDetails()) {
					if (mp.getMedicine().getMedicineCode().equals(ermd.getMedicineCode())) {
						mp.setAmount(mp.getAmount() - Math.abs(ermd.getQuantity()));
						break;
					}
				}
			}
			medicinePharmacyRepository.saveAll(toUpdatePharmacyStock);
			SimpleMailMessage mailMessage = new SimpleMailMessage();
	        mailMessage.setTo(patientDb.getEmail());
	        mailMessage.setSubject("Medicine issue via EReceipt from pharmacy " + eReceiptSearch.getPharmacy().getName());
	        mailMessage.setFrom(env.getProperty("spring.mail.username"));
	        String mailText = "You have been issued the following medication: \n";
	        for (EReceiptMedicineDetails ermd : eReceiptSearch.geteReceiptMedicineDetails()) {
	        	mailText += "\t" + ermd.getMedicineName() + ", Quantity: " + Math.abs(ermd.getQuantity()) + "\n";
	        }
	        mailText += "\tTotal price: " + Math.abs(eReceiptSearch.getTotal()) + "\n";
	        mailText += "\nBest regards,\nPharmacy " + eReceiptSearch.getPharmacy().getName();
	        mailMessage.setText(mailText);
	        emailSenderService.sendEmail(mailMessage);
		}
		return message;
	}

	@Transactional(readOnly = true)
	public Integer getDiscountByPatientCategory() {
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Patient patientDb = (Patient) repository.findById(patient.getId()).get();
		Category c = categoryRepository.findByCategoryName(patientDb.getCategoryName());
		return c.getDiscount();
	}
}
