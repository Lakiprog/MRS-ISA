package com.MRSISA2021_T15.repository;

import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DermatologistRepository extends CrudRepository<Dermatologist, Integer> {
	User findByEmail(String email);
	
	User findByUsername(String username);
}
