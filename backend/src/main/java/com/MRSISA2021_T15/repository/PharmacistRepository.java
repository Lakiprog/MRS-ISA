package com.MRSISA2021_T15.repository;

import com.MRSISA2021_T15.model.Pharmacist;
import com.MRSISA2021_T15.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacistRepository extends CrudRepository<Pharmacist, Integer> {

	User findByEmail(String email);
	
	User findByUsername(String username);
}