package com.MRSISA2021_T15.service;

import com.MRSISA2021_T15.model.AppointmentConsultationPoints;
import com.MRSISA2021_T15.model.Category;
import com.MRSISA2021_T15.model.CategoryName;

public interface LoyaltyProgramService {
	
	String defineCategories(Category category);
		
	CategoryName[] getCategoryNames();
	
	String definePointsForAppointmentAndConsulation(AppointmentConsultationPoints appointmentConsultationPoints);
}
