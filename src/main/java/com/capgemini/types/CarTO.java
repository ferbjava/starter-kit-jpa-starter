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

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public Integer getProductionYear() {
		return productionYear;
	}

	public String getColor() {
		return color;
	}

	public Integer getEngineCapacity() {
		return engineCapacity;
	}

	public Integer getEnginePower() {
		return enginePower;
	}

	public Integer getMileage() {
		return mileage;
	}

	public Long getId() {
		return id;
	}
	
	public static CarTOBuilder builder(){
		return new CarTOBuilder();
	}

	public static class CarTOBuilder {
		private Long id;
		private String type;
		private String brand;
		private String model;
		private Integer productionYear;
		private String color;
		private Integer engineCapacity;
		private Integer enginePower;
		private Integer mileage;
		
		public CarTOBuilder() {
			super();
		}
		
		public CarTOBuilder withId(Long id){
			this.id=id;
			return this;
		}
		
		public CarTOBuilder withType(String type){
			this.type=type;
			return this;
		}
		
		public CarTOBuilder withBrand(String brand){
			this.brand=brand;
			return this;
		}
		
		public CarTOBuilder withModel(String model){
			this.model=model;
			return this;
		}
		
		public CarTOBuilder withProductionYear(Integer productionYear){
			this.productionYear=productionYear;
			return this;
		}
		
		public CarTOBuilder withColor(String color){
			this.color=color;
			return this;
		}
		
		public CarTOBuilder withEngineCapacity(Integer engineCapacity){
			this.engineCapacity=engineCapacity;
			return this;
		}
		
		public CarTOBuilder withEnginePower(Integer enginePower){
			this.enginePower=enginePower;
			return this;
		}
		
		public CarTOBuilder withMileage(Integer mileage){
			this.mileage=mileage;
			return this;
		}
		
		public CarTO build(){
			checkBeforeBuild(type, brand, model, productionYear, color, engineCapacity, enginePower, mileage);
			return new CarTO(id, type, brand, model, productionYear, color, engineCapacity, enginePower, mileage);
		}

		private void checkBeforeBuild(String type, String brand, String model, Integer productionYear,
				String color, Integer engineCapacity, Integer enginePower, Integer mileage) {
			boolean isType=false;
			if(type!=null&&!type.isEmpty()){
				isType = true;
			}
			boolean isBrand=false;
			if(brand!=null&&!brand.isEmpty()){
				isBrand = true;
			}
			boolean isModel=false;
			if(model!=null&&!model.isEmpty()){
				isModel = true;
			}
			boolean isProductionYear=false;
			if(productionYear!=null){
				isProductionYear = true;
			}
			boolean isColor=false;
			if(color!=null&&!color.isEmpty()){
				isColor = true;
			}
			boolean isEngineCap=false;
			if(engineCapacity!=null){
				isEngineCap = true;
			}
			boolean isEnginePower=false;
			if(enginePower!=null){
				isEnginePower = true;
			}
			boolean isMileage=false;
			if(mileage!=null){
				isMileage = true;
			}
			
			if(!isType||!isBrand||!isModel||!isProductionYear||!isColor||!isEngineCap||!isEnginePower||!isMileage){
				throw new RuntimeException("Incorrect car to be created");
			}
		}
		
	}
	
	
}
