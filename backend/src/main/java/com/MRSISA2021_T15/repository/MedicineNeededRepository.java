package com.MRSISA2021_T15.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.model.MedicineNeeded;

@Repository
public interface MedicineNeededRepository extends CrudRepository<MedicineNeeded, Integer> {

}
