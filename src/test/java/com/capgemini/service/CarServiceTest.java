package com.capgemini.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.Utils.InsertData;
import com.capgemini.types.CarTO;
import com.capgemini.types.CarTO.CarTOBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest

public class CarServiceTest {

	@Autowired
	private CarService carService;

	@Test
	@Transactional
	public void shouldReturnExceptionDuringCarTOCreation() {
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

	@Test
	@Transactional
	public void shouldFindCarByIdWithoutDatabase() {
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
		assertEquals(savedCar.getId(), selectedCar.getId());
		assertEquals(savedCar.getBrand(), selectedCar.getBrand());
	}

	@Test
	@Transactional
	public void shouldAddCar() {
		// given
		long EXPECTED_START_CARS_NO=0;
		long EXPECTED_FINAL_CARS_NO=1;
		InsertData data = new InsertData();
		
		// when
		long startCarsNo = carService.findCarNo();
		carService.saveCar(data.getCarById(0));
		long finalCarsNo = carService.findCarNo();

		// then
		assertEquals(EXPECTED_START_CARS_NO, startCarsNo);
		assertEquals(EXPECTED_FINAL_CARS_NO, finalCarsNo);
	}

	@Test
	@Transactional
	public void shouldFindCarById() {
		// given
		InsertData data = new InsertData();
		CarTO savedCar = carService.saveCar(data.getCarById(0));

		// when
		CarTO selectedCar = carService.findCarById(savedCar.getId());

		// then
		assertNotNull(selectedCar);
		assertEquals(savedCar.getId(), selectedCar.getId());
		assertEquals(savedCar.getBrand(), selectedCar.getBrand());
	}

	@Test
	@Transactional
	public void shouldUpdateCar() {
		// given
		InsertData data = new InsertData();
		CarTO savedCar = carService.saveCar(data.getCarById(0));
		String EXPECTED_COLOR = "pink";

		// when
		long initialCarsNo = carService.findCarNo();
		CarTO updatedCar = new CarTOBuilder()
				.withId(savedCar.getId())
				.withType(savedCar.getType())
				.withBrand(savedCar.getBrand())
				.withModel(savedCar.getModel())
				.withProductionYear(savedCar.getProductionYear())
				.withColor(EXPECTED_COLOR)
				.withEngineCapacity(savedCar.getEngineCapacity())
				.withEnginePower(savedCar.getEnginePower())
				.withMileage(savedCar.getMileage())
				.build();
		CarTO updatedAndSavedCar = carService.updateCar(updatedCar);
		long finalCarsNo = carService.findCarNo();
		
		
		// then
		assertNotNull(updatedAndSavedCar);
		assertEquals(updatedAndSavedCar.getId(), savedCar.getId());
		assertEquals(EXPECTED_COLOR, updatedAndSavedCar.getColor());
		assertEquals(initialCarsNo, finalCarsNo);
	}

}
