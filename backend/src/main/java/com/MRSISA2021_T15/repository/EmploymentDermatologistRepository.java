package com.MRSISA2021_T15.repository;

import com.MRSISA2021_T15.model.Dermatologist;
import com.MRSISA2021_T15.model.Employment;
import com.MRSISA2021_T15.model.EmploymentDermatologist;
import com.MRSISA2021_T15.model.Pharmacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmploymentDermatologistRepository extends CrudRepository<Employment, Integer> {


    @Query("select d from EmploymentDermatologist d where pharmacy.id = ?1")
    public List<EmploymentDermatologist> findByPharmacyId(Integer id);

    public List<EmploymentDermatologist> findAllByPharmacy(Optional<Pharmacy> pharmacy);

    public List<EmploymentDermatologist> findAllByDermatologist(Dermatologist dermatologist);
}
