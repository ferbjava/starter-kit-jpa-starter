package com.capgemini.types;

public class CarTO {

	private Long id;
	private String type;
	private String brand;
	private String model;
	private Integer productionYear;
	private String color;
	private Integer engineCapacity;
	private Integer enginePower;
	private Integer mileage;

	/**
	 * Constructor without 'id' field
	 * @param type
	 * @param brand
	 * @param model
	 * @param productionYear
	 * @param color
	 * @param engineCapacity
	 * @param enginePower
	 * @param mileage
	 */
	public CarTO(String type, String brand, String model, Integer productionYear, String color, Integer engineCapacity,
			Integer enginePower, Integer mileage) {
		super();
		this.type = type;
		this.brand = brand;
		this.model = model;
		this.productionYear = productionYear;
		this.color = color;
		this.engineCapacity = engineCapacity;
		this.enginePower = enginePower;
		this.mileage = mileage;
	}
	
	/**
	 * Constructor with 'id' field
	 * @param id
	 * @param type
	 * @param brand
	 * @param model
	 * @param productionYear
	 * @param color
	 * @param engineCapacity
	 * @param enginePower
	 * @param mileage
	 */
	public CarTO(Long id, String type, String brand, String model, Integer productionYear, String color,
			Integer engineCapacity, Integer enginePower, Integer mileage) {
		super();
		this.id = id;
		this.type = type;
		this.brand = brand;
		this.model = model;
		this.productionYear = productionYear;
		this.color = color;
		this.engineCapacity = engineCapacity;
		this.enginePower = enginePower;
		this.mileage = mileage;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getProductionYear() {
		return productionYear;
	}

	public void setProductionYear(Integer productionYear) {
		this.productionYear = productionYear;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getEngineCapacity() {
		return engineCapacity;
	}

	public void setEngineCapacity(Integer engineCapacity) {
		this.engineCapacity = engineCapacity;
	}

	public Integer getEnginePower() {
		return enginePower;
	}

	public void setEnginePower(Integer enginePower) {
		this.enginePower = enginePower;
	}

	public Integer getMileage() {
		return mileage;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	public Long getId() {
		return id;
	}
	
//	public static CarTOBuilder builder(){
//		return new CarTOBuilder();
//	}
//
//	public static class CarTOBuilder {
//		
//	}
	
	
}
