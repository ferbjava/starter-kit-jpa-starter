package com.capgemini.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.domain.CarEntity;
import com.capgemini.domain.RentalEntity;
import com.capgemini.types.CarTO;
import com.capgemini.types.CarTO.CarTOBuilder;
import com.capgemini.types.RentalTO;

public class CarMapper {

	public static CarTO toCarTO(CarEntity entity) {
		if (entity == null) {
			return null;
		}
		Long id = entity.getId();
		String type = entity.getType();
		String brand = entity.getBrand();
		String model = entity.getModel();
		Integer productionYear = entity.getProductionYear();
		String color = entity.getColor();
		Integer engineCapacity = entity.getEngineCapacity();
		Integer enginePower = entity.getEnginePower();
		Integer mileage = entity.getMileage();
		List<RentalTO> rentals = RentalMapper.map2TOs(entity.getRentals());
		return new CarTOBuilder().withId(id).withType(type).withBrand(brand).withModel(model)
				.withProductionYear(productionYear).withColor(color).withEngineCapacity(engineCapacity)
				.withEnginePower(enginePower).withMileage(mileage).withRentalsTO(rentals).build();
	}

	public static CarEntity toCarEntity(CarTO carTO) {
		if (carTO == null) {
			return null;
		}
		Long id = carTO.getId();
		String type = carTO.getType();
		String brand = carTO.getBrand();
		String model = carTO.getModel();
		Integer productionYear = carTO.getProductionYear();
		String color = carTO.getColor();
		Integer engineCapacity = carTO.getEngineCapacity();
		Integer enginePower = carTO.getEnginePower();
		Integer mileage = carTO.getMileage();
		List<RentalEntity> rentals = RentalMapper.map2Entities(carTO.getRentals());
		return new CarEntity(id, type, brand, model, productionYear, color, engineCapacity, enginePower, mileage,
				rentals);
	}

	public static List<CarTO> map2TOs(List<CarEntity> entities) {
		if (entities == null) {
			return null;
		}
		return entities.stream().map(CarMapper::toCarTO).collect(Collectors.toList());
	}

	public static List<CarEntity> map2Entities(List<CarTO> carTOs) {
		if (carTOs == null) {
			return null;
		}
		return carTOs.stream().map(CarMapper::toCarEntity).collect(Collectors.toList());
	}

}
