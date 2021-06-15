package com.MRSISA2021_T15.repository;

import com.MRSISA2021_T15.model.Action;
import com.MRSISA2021_T15.model.Pharmacy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionRepository extends CrudRepository<Action, Integer> {
    public List<Action> findAllByPharmacy(Pharmacy pharmacy);
}
