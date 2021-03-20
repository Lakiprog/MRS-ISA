package com.MRSISA2021_T15.model;

public class Medicine {
	private String name, description;
	private double cost;
	private int amount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Medicine(String name, String description, double cost, int amount) {
		super();
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.amount = amount;
	}

}
