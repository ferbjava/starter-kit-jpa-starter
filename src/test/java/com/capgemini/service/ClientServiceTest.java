package com.capgemini.service;

import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.types.CarTO;
import com.capgemini.types.ClientTO;
import com.capgemini.types.DepartmentTO;
import com.capgemini.types.RentalTO;
import com.capgemini.types.RentalTO.RentalTOBuilder;
import com.capgemini.utils.InsertData;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
public class ClientServiceTest {

	@Autowired
	private ClientService clientService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private CarService carService;

	@Test
	@Transactional
	public void shouldAddClients() {
		// given
		InsertData data = new InsertData();
		data.initialize();
		final long EXPECTED_INITIAL_CLIENTS = 0;
		final long EXPECTED_FINAL_CLIENTS = 3;

		// when
		long initialClientsNo = clientService.findClientsNo();
		clientService.saveClient(data.getClientById(0));
		clientService.saveClient(data.getClientById(1));
		clientService.saveClient(data.getClientById(2));
		long finalClientsNo = clientService.findClientsNo();

		// then
		assertEquals(EXPECTED_INITIAL_CLIENTS, initialClientsNo);
		assertEquals(EXPECTED_FINAL_CLIENTS, finalClientsNo);
	}

	@Test
	@Transactional
	public void shouldAddRentals() {
		// given
		InsertData data = new InsertData();
		data.initialize();
		final long EXPECTED_INITIAL_RENTALS = 0;
		final long EXPECTED_FINAL_RENTALS = 3;

		ClientTO savedClient01 = clientService.saveClient(data.getClientById(0));
		ClientTO savedClient02 = clientService.saveClient(data.getClientById(1));
		ClientTO savedClient03 = clientService.saveClient(data.getClientById(3));
		DepartmentTO savedDepart01 = departmentService.saveDepartment(data.getDepById(0));
		DepartmentTO savedDepart02 = departmentService.saveDepartment(data.getDepById(1));
		DepartmentTO savedDepart03 = departmentService.saveDepartment(data.getDepById(2));
		CarTO savedCar01 = carService.saveCar(data.getCarById(0));
		CarTO savedCar02 = carService.saveCar(data.getCarById(1));
		CarTO savedCar03 = carService.saveCar(data.getCarById(2));

		// when
		long initialRentalsNo = clientService.findRentalsNo();
		RentalTO rental01 = new RentalTOBuilder().withDateStart(new GregorianCalendar(2018, 5, 20))
				.withDateStop(new GregorianCalendar(2018, 6, 15)).withCharge(130.0).build();
		clientService.saveRental(rental01, savedClient01.getId(), savedDepart01.getId(), savedDepart01.getId(), savedCar01.getId());
		RentalTO rental02 = new RentalTOBuilder().withDateStart(new GregorianCalendar(2018, 1, 1))
				.withDateStop(new GregorianCalendar(2018, 1, 19)).withCharge(1000.0).build();
		clientService.saveRental(rental02, savedClient02.getId(), savedDepart02.getId(), savedDepart02.getId(), savedCar02.getId());
		RentalTO rental03 = new RentalTOBuilder().withDateStart(new GregorianCalendar(2018, 2, 28))
				.withDateStop(new GregorianCalendar(2018, 3, 8)).withCharge(500.0).build();
		clientService.saveRental(rental03, savedClient03.getId(), savedDepart03.getId(), savedDepart02.getId(), savedCar03.getId());
		long finalRentalsNo = clientService.findRentalsNo();

		// then
		assertEquals(EXPECTED_INITIAL_RENTALS, initialRentalsNo);
		assertEquals(EXPECTED_FINAL_RENTALS, finalRentalsNo);
	}

}
