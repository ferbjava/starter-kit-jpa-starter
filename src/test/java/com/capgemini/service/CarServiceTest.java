package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.Utils.InsertData;
import com.capgemini.types.CarTO;
import com.capgemini.types.CarTO.CarTOBuilder;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.PositionTO;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
public class CarServiceTest {

	@Autowired
	private CarService carService;

	@Autowired
	private DepartmentService departmentService;

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
		final long EXPECTED_START_CARS_NO=0;
		final long EXPECTED_FINAL_CARS_NO=1;
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
		final String EXPECTED_COLOR = "pink";

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

	@Test
	@Transactional
	public void shouldFindCarsByEmployee() {
		// given
		InsertData data = new InsertData();
		final long EXPECTED_SELECTED_CARS = 2;
		PositionTO seller = departmentService.savePosition(data.getPosById(1));
		EmployeeTO emp01 = departmentService.saveEmployee(data.getEmplById(0), null, seller.getId());
		EmployeeTO emp02 = departmentService.saveEmployee(data.getEmplById(1), null, seller.getId());
		CarTO car01 = carService.saveCar(data.getCarById(0));
		CarTO car02 = carService.saveCar(data.getCarById(1));
		CarTO car03 = carService.saveCar(data.getCarById(2));
		carService.addCarer(car01.getId(), emp01.getId());
		carService.addCarer(car02.getId(), emp01.getId());
		carService.addCarer(car03.getId(), emp02.getId());

		// when
		List<CarTO> selectedCars = carService.findCarsByEmployee(emp01.getId());

		// then
		assertEquals(EXPECTED_SELECTED_CARS, selectedCars.size());
		assertEquals(car01.getId(), selectedCars.get(0).getId());
		assertEquals(car02.getId(), selectedCars.get(1).getId());
	}

	@Test
	@Transactional
	public void shouldRemoveCarsWithoutCarer() {
		// given
		InsertData data = new InsertData();
		final long EXPECTED_INITIAL_CARS = 2;
		final long EXPECTED_FINAL_CARS = 1;
		EmployeeTO emp01 = departmentService.saveEmployee(data.getEmplById(0), null, null);
		CarTO car01 = carService.saveCar(data.getCarById(0));
		CarTO car02 = carService.saveCar(data.getCarById(1));
		carService.addCarer(car02.getId(), emp01.getId());

		// when
		long initialCarsNo = carService.findCarNo();
		carService.deleteCar(car01.getId());
		long finalCarsNo = carService.findCarNo();
		
		// then
		assertEquals(EXPECTED_INITIAL_CARS, initialCarsNo);
		assertEquals(EXPECTED_FINAL_CARS, finalCarsNo);
	}

	@Test
	@Transactional
	public void shouldRemoveCarsWithCarer() {
		// given
		InsertData data = new InsertData();
		final long EXPECTED_INITIAL_CARS = 2;
		final long EXPECTED_FINAL_CARS = 1;
		final long EXPECTED_INITIAL_CARS_UNDER_EMPLOYEE = 1;
		final long EXPECTED_FINAL_CARS_UNDER_EMPLOYEE = 0;
		EmployeeTO emp01 = departmentService.saveEmployee(data.getEmplById(0), null, null);
		carService.saveCar(data.getCarById(0));
		CarTO car02 = carService.saveCar(data.getCarById(1));
		carService.addCarer(car02.getId(), emp01.getId());

		// when
		long initialCarsNo = carService.findCarNo();
		long initialCarsUnderEmployee = carService.findCarsByEmployee(emp01.getId()).size();
		carService.deleteCar(car02.getId());
		long finalCarsNo = carService.findCarNo();
		long finalCarsUnderEmployee = carService.findCarsByEmployee(emp01.getId()).size();
		
		// then
		assertEquals(EXPECTED_INITIAL_CARS, initialCarsNo);
		assertEquals(EXPECTED_FINAL_CARS, finalCarsNo);
		assertEquals(EXPECTED_INITIAL_CARS_UNDER_EMPLOYEE, initialCarsUnderEmployee);
		assertEquals(EXPECTED_FINAL_CARS_UNDER_EMPLOYEE, finalCarsUnderEmployee);
	}

	@Test
	@Transactional
	public void shouldFindCarByType() {
		// given
		InsertData data = new InsertData();
		final long EXPECTED_CARS = 3;
		final long EXPECTED_CARS_BY_TYPE = 2;
		final String EXPECTED_TYPE = "combii";
		carService.saveCar(data.getCarById(0));
		carService.saveCar(data.getCarById(1));
		carService.saveCar(data.getCarById(2));

		// when
		long finalCarsNo = carService.findCarNo();
		List<CarTO> carsByType = carService.findCarsByType(EXPECTED_TYPE);
		long ByTypeNocars = carsByType.size();
		
		// then
		assertEquals(EXPECTED_CARS, finalCarsNo);
		assertEquals(EXPECTED_CARS_BY_TYPE, ByTypeNocars);
		for(CarTO selectedCar:carsByType){
			assertEquals(EXPECTED_TYPE, selectedCar.getType());
		}
	}

	@Test
	@Transactional
	public void shouldFindCarByBrand() {
		// given
		InsertData data = new InsertData();
		final long EXPECTED_CARS = 3;
		final long EXPECTED_CARS_BY_BRAND = 2;
		final String EXPECTED_BRAND = "Skoda";
		carService.saveCar(data.getCarById(0));
		carService.saveCar(data.getCarById(1));
		carService.saveCar(data.getCarById(7));

		// when
		long finalCarsNo = carService.findCarNo();
		List<CarTO> carsByBrand = carService.findCarsByBrand(EXPECTED_BRAND);
		long ByTypeNocars = carsByBrand.size();
		
		// then
		assertEquals(EXPECTED_CARS, finalCarsNo);
		assertEquals(EXPECTED_CARS_BY_BRAND, ByTypeNocars);
		for(CarTO selectedCar:carsByBrand){
			assertEquals(EXPECTED_BRAND, selectedCar.getBrand());
		}
	}

}
