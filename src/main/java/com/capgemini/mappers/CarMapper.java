package com.capgemini.mappers;

import java.util.Set;
import java.util.stream.Collectors;

import com.capgemini.domain.CarEntity;
import com.capgemini.types.CarTO;
import com.capgemini.types.CarTO.CarTOBuilder;

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
		return new CarTOBuilder().withId(id).withType(type).withBrand(brand).withModel(model)
				.withProductionYear(productionYear).withColor(color).withEngineCapacity(engineCapacity)
				.withEnginePower(enginePower).withMileage(mileage).build();
	}

	public static CarEntity toCarEntity(CarTO carTO) {
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

	public static Set<CarTO> map2TOs(Set<CarEntity> entities) {
		if (entities == null) {
			return null;
		}
		return entities.stream().map(CarMapper::toCarTO).collect(Collectors.toSet());
	}

	public static Set<CarEntity> map2Entities(Set<CarTO> carTOs) {
		if (carTOs == null) {
			return null;
		}
		return carTOs.stream().map(CarMapper::toCarEntity).collect(Collectors.toSet());
	}

}
