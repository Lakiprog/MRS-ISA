package com.MRSISA2021_T15.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MRSISA2021_T15.model.AppointmentConsultationPoints;
import com.MRSISA2021_T15.model.Category;
import com.MRSISA2021_T15.model.CategoryName;
import com.MRSISA2021_T15.repository.AppointmentConsultationPointsRepository;
import com.MRSISA2021_T15.repository.CategoryRepository;

@Service
public class LoyaltyProgramServiceImpl implements LoyaltyProgramService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private AppointmentConsultationPointsRepository appointmentConsultationPointsRepository;
	
	@Override
	public String defineCategories(Category category) {
		String message = "";
		Category ca = categoryRepository.findByCategoryName(category.getCategoryName());
		if (ca != null) {
			ca.setDiscount(category.getDiscount());
			ca.setRequiredNumberOfPoints(category.getRequiredNumberOfPoints());
			categoryRepository.save(ca);
			message = "Category information updated.";
		} else {
			categoryRepository.save(category);
			message = "Category successfully defined.";
			
		}
		return message;
	}
	
	@Override
	public String definePointsForAppointmentAndConsulation(AppointmentConsultationPoints appointmentConsultationPoints) {
		String message = "";
		AppointmentConsultationPoints acp = appointmentConsultationPointsRepository.findByType(appointmentConsultationPoints.getType());
		if (acp != null) {
			acp.setPoints(appointmentConsultationPoints.getPoints());
			appointmentConsultationPointsRepository.save(acp);
			message = "Points for " + appointmentConsultationPoints.getType() + " successfully updated.";
		} else {
			appointmentConsultationPointsRepository.save(appointmentConsultationPoints);
			message = "Points for " + appointmentConsultationPoints.getType() + " successfully defined.";
		}
		return message;
	}

	@Override
	public CategoryName[] getCategoryNames() {
		CategoryName[] cn = {CategoryName.SILVER, CategoryName.GOLD};
		return cn;
	}
}
