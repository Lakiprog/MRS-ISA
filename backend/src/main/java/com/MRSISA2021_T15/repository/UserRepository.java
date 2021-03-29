package com.MRSISA2021_T15.repository;

import com.MRSISA2021_T15.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserRepository extends CrudRepository<User, Integer> {
	
	User findByEmail(String email);

}