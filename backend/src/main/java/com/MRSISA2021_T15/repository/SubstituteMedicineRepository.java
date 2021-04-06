package com.MRSISA2021_T15.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.model.SubstituteMedicine;

@Repository
public interface SubstituteMedicineRepository extends CrudRepository<SubstituteMedicine, Integer> {

}
