package com.MRSISA2021_T15.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "absence")
public class Absence {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public LocalDateTime getStart() {
		return start;
	}
	
	public void setStart(LocalDateTime start) {
		this.start = start;
	}
	
	public LocalDateTime getEnd() {
		return end;
	}
	
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public User getDoctor() {
		return doctor;
	}
	
	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}
	
	public boolean isApproved() {
		return approved;
	}
	
	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	@Column
	private LocalDateTime start, end;
	
	@Column
	private String description;
	
	@Column
	private boolean approved;
	
	@ManyToOne
	private User doctor;
}
