package com.MRSISA2021_T15.repository;

import com.MRSISA2021_T15.model.Pharmacy;
import com.MRSISA2021_T15.model.Promotion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PromotionRepository extends CrudRepository<Promotion, Integer> {
	
	@Query("select p from Promotion p where p.pharmacy.id = ?1 AND p.endingDate > ?2")
	List<Promotion> getPromotionByPharmacyId(Integer pharmacyId, LocalDate currentDate);

	public List<Promotion> findAllByPharmacy(Pharmacy pharmacy);

}
