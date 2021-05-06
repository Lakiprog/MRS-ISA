package com.MRSISA2021_T15.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.model.Promotion;

@Repository
public interface PromotionRepository extends CrudRepository<Promotion, Integer> {
	
	@Query("select p.description from Promotion p where p.pharmacy.id = ?1")
	String getDescriptionByPharmacyId(Integer pharmacyId);

}
