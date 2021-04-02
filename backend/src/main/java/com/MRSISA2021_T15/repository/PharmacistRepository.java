package com.MRSISA2021_T15.repository;

import com.MRSISA2021_T15.model.Pharmacist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacistRepository extends CrudRepository<Pharmacist, Integer> {

}