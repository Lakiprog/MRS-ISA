package com.MRSISA2021_T15.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.MRSISA2021_T15.model.Pharmacy;

public interface PharmacySearchRepository extends JpaRepository<Pharmacy, Integer>{
	
}
