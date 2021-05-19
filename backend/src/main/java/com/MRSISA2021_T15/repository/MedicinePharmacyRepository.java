package com.MRSISA2021_T15.repository;
import com.MRSISA2021_T15.model.MedicinePharmacy;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MedicinePharmacyRepository extends CrudRepository<MedicinePharmacy, Integer> {
	
	@Query("select m from MedicinePharmacy m where pharmacy.id = ?1")
	public List<MedicinePharmacy> findByPharmacyId(Integer id);
	
	@Query("select m from MedicinePharmacy m where pharmacy.id = ?1 and medicine.id = ?1")
	public MedicinePharmacy findByExact(Integer pharmacyId, Integer medicineId);
	

	@Query("select m from MedicinePharmacy m where m.id = ?1")
	public MedicinePharmacy findUsingId(Integer id);

	@Query("select mp from MedicinePharmacy mp where mp.pharmacy.id = ?1 AND mp.medicine.medicineCode like ?2 AND mp.amount >= ?3")
	public MedicinePharmacy getPharmacyByIdAndMedicineCode(Integer pharmacyId, String medicineCode, Integer quantity);

	
}
