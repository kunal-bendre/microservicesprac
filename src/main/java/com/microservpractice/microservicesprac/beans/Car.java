package com.microservpractice.microservicesprac.beans;

import javax.validation.constraints.Size;

public class Car {

	private Integer id;
	private String make;
	private String model;
	@Size(min = 2 , message = "Color should be valid color")
	private String color;
	private Integer year;
	private Double price;


	public Car(Integer id, String make, String model, String color, Integer year,
			Double price) {
		this.id = id;
		this.make = make;
		this.model = model;
		this.color = color;
		this.year = year;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public String getColor() {
		return color;
	}

	public Integer getYear() {
		return year;
	}

	public Double getPrice() {
		return price;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Car{" +
				"id=" + id +
				", make='" + make + '\'' +
				", model='" + model + '\'' +
				", color='" + color + '\'' +
				", year=" + year +
				", price=" + price +
				'}';
	}
}
