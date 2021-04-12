package com.MRSISA2021_T15.repository;

import com.MRSISA2021_T15.model.Patient;
import com.MRSISA2021_T15.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	User findByEmail(String email);
	
	User findByUsername(String username);
	
	List<User> findAll();
	
	@Query("select distinct p from User p where USER_TYPE = 'PATIENT'")
	List<Patient>findAllPatients();
	


}