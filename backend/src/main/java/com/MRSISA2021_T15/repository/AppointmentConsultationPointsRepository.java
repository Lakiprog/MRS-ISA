package com.MRSISA2021_T15.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.model.AppointmentConsultationPoints;

@Repository
public interface AppointmentConsultationPointsRepository extends CrudRepository<AppointmentConsultationPoints, Integer> {
	
	AppointmentConsultationPoints findByType(String type);
	
	@Query("select acp.points from AppointmentConsultationPoints acp where acp.type like ?1")
	double getPointsByType(String type);
}
