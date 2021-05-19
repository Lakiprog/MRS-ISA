package com.MRSISA2021_T15.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.model.Appointment;
import com.MRSISA2021_T15.model.ReservationItem;

@Repository
public interface ReservationItemRepository extends CrudRepository<ReservationItem, Integer>{
	
}
