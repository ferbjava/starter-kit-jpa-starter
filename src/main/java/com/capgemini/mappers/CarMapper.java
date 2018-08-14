package com.capgemini.mappers;

import com.capgemini.domain.CarEntity;
import com.capgemini.types.CarTO;

public class CarMapper {

	public static CarTO toCarTO(CarEntity carEntity) {
		if (carEntity == null) {
			return null;
		}
		Long id = carEntity.getId();
		String type = carEntity.getType();
		String brand = carEntity.getBrand();
		String model = carEntity.getModel();
		Integer productionYear = carEntity.getProductionYear();
		String color = carEntity.getColor();
		Integer engineCapacity = carEntity.getEngineCapacity();
		Integer enginePower = carEntity.getEnginePower();
		Integer mileage = carEntity.getMileage();

		return new CarTO(id, type, brand, model, productionYear, color, engineCapacity, enginePower, mileage);
	}
	
	public static CarEntity toCarEntity (CarTO carTO){
		if (carTO == null) {
			return null;
		}
		String type = carTO.getType();
		String brand = carTO.getBrand();
		String model = carTO.getModel();
		Integer productionYear = carTO.getProductionYear();
		String color = carTO.getColor();
		Integer engineCapacity = carTO.getEngineCapacity();
		Integer enginePower = carTO.getEnginePower();
		Integer mileage = carTO.getMileage();
		return new CarEntity(type, brand, model, productionYear, color, engineCapacity, enginePower, mileage);
	}
	
	
}
