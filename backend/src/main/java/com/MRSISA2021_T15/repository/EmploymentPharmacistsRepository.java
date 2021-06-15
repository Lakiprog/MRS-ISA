package com.MRSISA2021_T15.repository;

import com.MRSISA2021_T15.model.Employment;
import com.MRSISA2021_T15.model.EmploymentPharmacist;
import com.MRSISA2021_T15.model.Pharmacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmploymentPharmacistsRepository extends CrudRepository<Employment, Integer> {

    @Query("select d from EmploymentPharmacist d where pharmacy.id = ?1")
    public List<EmploymentPharmacist> findByPharmacyId(Integer id);

    @Query("select d from EmploymentPharmacist d where pharmacist.id = ?1")
    EmploymentPharmacist findByPharmacistId(Integer id);

    public List<EmploymentPharmacist> findAllByPharmacy(Optional<Pharmacy> pharmacy);
}
