package com.MRSISA2021_T15.model;

import java.util.Date;

public class Promotion {
	Date startingDate;
	Date endingDate;
	String description;

	public Date getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}

	public Date getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Promotion(Date startingDate, Date endingDate, String description) {
		super();
		this.startingDate = startingDate;
		this.endingDate = endingDate;
		this.description = description;
	}
}
