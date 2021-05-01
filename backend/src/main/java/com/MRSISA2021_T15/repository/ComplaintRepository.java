package com.MRSISA2021_T15.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.MRSISA2021_T15.model.Complaint;
import com.MRSISA2021_T15.model.SystemAdmin;

@Repository
public interface ComplaintRepository extends CrudRepository<Complaint, Integer> {
	
	@Query("select complaint from Complaint complaint where complaint.systemAdmin.id is null AND complaint.response is null")
	List<Complaint> getComplaintsToRespond();
	
	List<Complaint> findBySystemAdmin(SystemAdmin systemAdmin);

}