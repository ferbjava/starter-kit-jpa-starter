package com.capgemini.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.types.CarTO;
import com.capgemini.types.CarTO.CarTOBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest

public class CarServiceTest {

	@Autowired
	private CarService carService;

	@Test
	@Transactional
	public void shouldFindCarById() {
		// given
		final String type = "combii";
		final String brand = "VW";
		final String model = "Golf";
		final Integer productionYear = 2010;
		final String color = "black";
		final Integer engineCapacity = 1998;
		final Integer enginePower = 250;
		final Integer mileage = 170000;

		CarTO car = new CarTOBuilder().withType(type).withBrand(brand).withModel(model)
				.withProductionYear(productionYear).withColor(color).withEngineCapacity(engineCapacity)
				.withEnginePower(enginePower).withMileage(mileage).build();
		CarTO savedCar = carService.saveCar(car);

		// when
		CarTO selectedCar = carService.findCarById(savedCar.getId());

		// then
		assertNotNull(selectedCar);
		assertEquals(savedCar.getBrand(), selectedCar.getBrand());
		assertEquals(savedCar.getId(), selectedCar.getId());

	}

	@Test
	@Transactional
	public void shouldReturnExceptionDuringCreation() {
		// given
		final String type = "combii";
		final String brand = "VW";
		final String model = "Golf";
		final Integer productionYear = 2010;
		final String color = null;
		final Integer engineCapacity = 1998;
		final Integer enginePower = 250;
		final Integer mileage = 170000;
		boolean isException = false;

		// when
		try {
			new CarTOBuilder().withType(type).withBrand(brand).withModel(model).withProductionYear(productionYear)
					.withColor(color).withEngineCapacity(engineCapacity).withEnginePower(enginePower)
					.withMileage(mileage).build();
		} catch (RuntimeException ex) {
			isException = true;
		}

		// then
		assertTrue(isException);

	}

}
