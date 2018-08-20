package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.types.CarTO;
import com.capgemini.types.DepartmentTO;
import com.capgemini.types.EmployeeSearchCriteriaTO;
import com.capgemini.types.DepartmentTO.DepartmentTOBuilder;
import com.capgemini.utils.InsertData;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.PositionTO;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
public class DepartmentServiceTest {

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private CarService carService;

	@Test
	@Transactional
	public void shouldAddPositions() {
		// given
		InsertData data = new InsertData();
		data.initialize();
		
		final long EXPECTED_INITIAL_POSITIONS_NUMBER = 0;
		final long EXPECTED_FINAL_POSITIONS_NUMBER = 3;

		// when
		long initialPositionsNo = departmentService.findPositionsNo();
		departmentService.savePosition(data.getPosById(0));
		departmentService.savePosition(data.getPosById(1));
		departmentService.savePosition(data.getPosById(2));
		long finalPositionsNo = departmentService.findPositionsNo();

		// then
		assertEquals(EXPECTED_INITIAL_POSITIONS_NUMBER, initialPositionsNo);
		assertEquals(EXPECTED_FINAL_POSITIONS_NUMBER, finalPositionsNo);
	}

	@Test
	@Transactional
	public void shouldAddEmployeeWithPosition() {
		// given
		InsertData data = new InsertData();
		data.initialize();
		
		final long EXPECTED_INITIAL_EMPLOYEES_NUMBER = 0;
		final long EXPECTED_FINAL_EMPLOYEES_NUMBER = 1;
		departmentService.savePosition(data.getPosById(0));
		PositionTO savedPos2 = departmentService.savePosition(data.getPosById(1));
		departmentService.savePosition(data.getPosById(2));

		// when
		long initialEmployeesNo = departmentService.findEmployeesNo();
		departmentService.saveEmployee(data.getEmplById(0), null, savedPos2.getId());
		long finalEmployeesNo = departmentService.findEmployeesNo();

		// then
		assertEquals(EXPECTED_INITIAL_EMPLOYEES_NUMBER, initialEmployeesNo);
		assertEquals(EXPECTED_FINAL_EMPLOYEES_NUMBER, finalEmployeesNo);
	}

	@Test
	@Transactional
	public void shouldFindEmployeeById() {
		// given
		InsertData data = new InsertData();
		data.initialize();
		
		departmentService.savePosition(data.getPosById(0));
		PositionTO savedPos2 = departmentService.savePosition(data.getPosById(1));
		departmentService.savePosition(data.getPosById(2));
		EmployeeTO savedEmp01 = departmentService.saveEmployee(data.getEmplById(0), null, savedPos2.getId());

		// when
		EmployeeTO selectedEmp01 = departmentService.findEmployeeById(savedEmp01.getId());

		// then
		assertNotNull(selectedEmp01);
		assertEquals(savedEmp01.getFirstName(), selectedEmp01.getFirstName());
		assertEquals(savedEmp01.getId(), selectedEmp01.getId());
	}

	@Test
	@Transactional
	public void shouldRemovePositionAndChildEmployees() {
		// given
		InsertData data = new InsertData();
		data.initialize();
		
		final long EXPECTED_INITIAL_POSITIONS_NUMBER = 3;
		final long EXPECTED_FINAL_POSITIONS_NUMBER = 2;
		final long EXPECTED_INITIAL_EMPLOYEES_NUMBER = 1;
		final long EXPECTED_FINAL_EMPLOYEES_NUMBER = 0;
		departmentService.savePosition(data.getPosById(0));
		PositionTO savedPos2 = departmentService.savePosition(data.getPosById(1));
		departmentService.savePosition(data.getPosById(2));
		departmentService.saveEmployee(data.getEmplById(0), null, savedPos2.getId());

		// when
		long initialPositionsNumber = departmentService.findPositionsNo();
		long initialEmployeesNumber = departmentService.findEmployeesNo();
		departmentService.deletePosition(savedPos2.getId());
		long finalPositionsNumber = departmentService.findPositionsNo();
		long finalEmployeesNumber = departmentService.findEmployeesNo();

		// then
		assertEquals(EXPECTED_INITIAL_POSITIONS_NUMBER, initialPositionsNumber);
		assertEquals(EXPECTED_INITIAL_EMPLOYEES_NUMBER, initialEmployeesNumber);
		assertEquals(EXPECTED_FINAL_POSITIONS_NUMBER, finalPositionsNumber);
		assertEquals(EXPECTED_FINAL_EMPLOYEES_NUMBER, finalEmployeesNumber);
	}

	@Test
	@Transactional
	public void shouldRemoveOnlyEmployee() {
		// given
		InsertData data = new InsertData();
		data.initialize();
		
		final long EXPECTED_INITIAL_POSITIONS_NUMBER = 3;
		final long EXPECTED_FINAL_POSITIONS_NUMBER = 3;
		final long EXPECTED_INITIAL_DEPARTMENTS_NUMBER = 1;
		final long EXPECTED_FINAL_DEPARTMENTS_NUMBER = 1;
		final long EXPECTED_INITIAL_EMPLOYEES_NUMBER = 1;
		final long EXPECTED_FINAL_EMPLOYEES_NUMBER = 0;
		departmentService.savePosition(data.getPosById(0));
		PositionTO savedPos2 = departmentService.savePosition(data.getPosById(1));
		departmentService.savePosition(data.getPosById(2));
		DepartmentTO savedDep01 = departmentService.saveDepartment(data.getDepById(0));
		EmployeeTO savedEmp01 = departmentService.saveEmployee(data.getEmplById(0), savedDep01.getId(), savedPos2.getId());

		// when
		long initialPositionsNumber = departmentService.findPositionsNo();
		long initialDepartmentsNumber = departmentService.findDepartmentNo();
		long initialEmployeesNumber = departmentService.findEmployeesNo();
		departmentService.deleteEmployee(savedEmp01.getId());
		long finalPositionsNumber = departmentService.findPositionsNo();
		long finalDepartmentsNumber = departmentService.findDepartmentNo();
		long finalEmployeesNumber = departmentService.findEmployeesNo();

		// then
		assertEquals(EXPECTED_INITIAL_POSITIONS_NUMBER, initialPositionsNumber);
		assertEquals(EXPECTED_INITIAL_DEPARTMENTS_NUMBER, initialDepartmentsNumber);
		assertEquals(EXPECTED_INITIAL_EMPLOYEES_NUMBER, initialEmployeesNumber);
		assertEquals(EXPECTED_FINAL_POSITIONS_NUMBER, finalPositionsNumber);
		assertEquals(EXPECTED_FINAL_DEPARTMENTS_NUMBER, finalDepartmentsNumber);
		assertEquals(EXPECTED_FINAL_EMPLOYEES_NUMBER, finalEmployeesNumber);
	}

	@Test
	@Transactional
	public void shouldAddDepartments() {
		// given
		InsertData data = new InsertData();
		data.initialize();
		
		final long EXPECTED_INITIAL_DEPARTMENTS_NUMBER = 0;
		final long EXPECTED_FINAL_DEPARTMENTS_NUMBER = 3;

		// when
		long initialDepartmentsNo = departmentService.findDepartmentNo();
		departmentService.saveDepartment(data.getDepById(0));
		departmentService.saveDepartment(data.getDepById(1));
		departmentService.saveDepartment(data.getDepById(2));
		long finalDepartmentsNo = departmentService.findDepartmentNo();

		// then
		assertEquals(EXPECTED_INITIAL_DEPARTMENTS_NUMBER, initialDepartmentsNo);
		assertEquals(EXPECTED_FINAL_DEPARTMENTS_NUMBER, finalDepartmentsNo);
	}

	@Test
	@Transactional
	public void shouldAddEmployeesToDepartment() {
		// given
		InsertData data = new InsertData();
		data.initialize();
		
		final long EXPECTED_INITIAL_EMPLOYEES_NUMBER = 0;
		final long EXPECTED_FINAL_EMPLOYEES_NUMBER = 2;
		DepartmentTO savedDep01 = departmentService.saveDepartment(data.getDepById(0));
		departmentService.saveDepartment(data.getDepById(1));
		departmentService.saveDepartment(data.getDepById(2));
		PositionTO savedPos01 = departmentService.savePosition(data.getPosById(0));
		PositionTO savedPos02 = departmentService.savePosition(data.getPosById(1));
		departmentService.savePosition(data.getPosById(2));

		// when
		long initialEmployeesNo = departmentService.findEmployeesNo();
		departmentService.saveEmployee(data.getEmplById(0), savedDep01.getId(), savedPos01.getId());
		departmentService.saveEmployee(data.getEmplById(1), savedDep01.getId(), savedPos02.getId());
		long finalEmployeesNo = departmentService.findEmployeesNo();

		// then
		assertEquals(EXPECTED_INITIAL_EMPLOYEES_NUMBER, initialEmployeesNo);
		assertEquals(EXPECTED_FINAL_EMPLOYEES_NUMBER, finalEmployeesNo);
	}

	@Test
	@Transactional
	public void shouldUpdateDepartmentData() {
		// given
		InsertData data = new InsertData();
		data.initialize();
		
		final long EXPECTED_INITIAL_DEPARTMENTS_NUMBER = 1;
		final long EXPECTED_FINAL_DEPARTMENTS_NUMBER = 1;
		String EXPECTED_NEW_EMAIL = "jakis.email@gmail.com";
		DepartmentTO savedDep01 = departmentService.saveDepartment(data.getDepById(0));
		long initialDepartmentsNo = departmentService.findDepartmentNo();

		// when
		DepartmentTO updatedDepartment = new DepartmentTOBuilder()
											.withId(savedDep01.getId())
											.withAdress(savedDep01.getAdress())
											.withEmail(EXPECTED_NEW_EMAIL)
											.withPhoneNumber(savedDep01.getPhoneNumber())
											.build();
		DepartmentTO updatedAndSavedDepartment = departmentService.updateDepartment(updatedDepartment);
		long finalDepartmentsNo = departmentService.findDepartmentNo();

		// then
		assertEquals(EXPECTED_INITIAL_DEPARTMENTS_NUMBER, initialDepartmentsNo);
		assertEquals(EXPECTED_FINAL_DEPARTMENTS_NUMBER, finalDepartmentsNo);
		assertEquals(savedDep01.getAdress(), updatedAndSavedDepartment.getAdress());
		assertEquals(EXPECTED_NEW_EMAIL, updatedAndSavedDepartment.getEmail());
	}

	@Test
	@Transactional
	public void shouldRemoveDepartment() {
		// given
		InsertData data = new InsertData();
		data.initialize();
		
		final long EXPECTED_INITIAL_DEPARTMENTS_NUMBER = 2;
		final long EXPECTED_FINAL_DEPARTMENTS_NUMBER = 1;
		final long EXPECTED_INITIAL_POSITIONS_NUMBER = 2;
		final long EXPECTED_FINAL_POSITIONS_NUMBER = 2;
		final long EXPECTED_INITIAL_EMPLOYEES_NUMBER = 3;
		final long EXPECTED_FINAL_EMPLOYEES_NUMBER = 1;
		departmentService.savePosition(data.getPosById(0));
		departmentService.savePosition(data.getPosById(1));
		DepartmentTO savedDep01 = departmentService.saveDepartment(data.getDepById(0));
		DepartmentTO savedDep02 = departmentService.saveDepartment(data.getDepById(1));
		departmentService.saveEmployee(data.getEmplById(0), savedDep01.getId(), null);
		departmentService.saveEmployee(data.getEmplById(1), savedDep01.getId(), null);
		departmentService.saveEmployee(data.getEmplById(2), savedDep02.getId(), null);
		long initialDepartmentsNo = departmentService.findDepartmentNo();
		long initialPositionsNo = departmentService.findPositionsNo();
		long initialEmployeesNo = departmentService.findEmployeesNo();

		// when
		departmentService.deleteDepartment(savedDep01.getId());
		long finalDepartmentsNo = departmentService.findDepartmentNo();
		long finalPositionsNo = departmentService.findPositionsNo();
		long finalEmployeesNo = departmentService.findEmployeesNo();

		// then
		assertEquals(EXPECTED_INITIAL_DEPARTMENTS_NUMBER, initialDepartmentsNo);
		assertEquals(EXPECTED_FINAL_DEPARTMENTS_NUMBER, finalDepartmentsNo);
		assertEquals(EXPECTED_INITIAL_POSITIONS_NUMBER, initialPositionsNo);
		assertEquals(EXPECTED_FINAL_POSITIONS_NUMBER, finalPositionsNo);
		assertEquals(EXPECTED_INITIAL_EMPLOYEES_NUMBER, initialEmployeesNo);
		assertEquals(EXPECTED_FINAL_EMPLOYEES_NUMBER, finalEmployeesNo);
	}

	@Test
	@Transactional
	public void shouldFindEmployeeFromDepartment() {
		// given
		InsertData data = new InsertData();
		data.initialize();
		
		final int EXPECTED_FOUND_EMPLOYEES_NUMBER = 2;
		DepartmentTO savedDep01 = departmentService.saveDepartment(data.getDepById(0));
		DepartmentTO savedDep02 = departmentService.saveDepartment(data.getDepById(1));
		departmentService.saveEmployee(data.getEmplById(0), savedDep01.getId(), null);
		EmployeeTO savedEmp02 = departmentService.saveEmployee(data.getEmplById(1), savedDep02.getId(), null);
		EmployeeTO savedEmp03 = departmentService.saveEmployee(data.getEmplById(2), savedDep02.getId(), null);

		// when
		List<EmployeeTO> employeeFrom2Department = departmentService.findAllEmployeesFromDepartment(savedDep02.getId());
		int employeesNo = employeeFrom2Department.size();

		// then
		assertEquals(EXPECTED_FOUND_EMPLOYEES_NUMBER, employeesNo);
		assertEquals(savedEmp02.getId(), employeeFrom2Department.get(0).getId());
		assertEquals(savedEmp03.getId(), employeeFrom2Department.get(1).getId());
	}

	@Test
	@Transactional
	public void shouldFindEmployeeFromDepartmentWithCar() {
		// given
		InsertData data = new InsertData();
		data.initialize();
		
		final int EXPECTED_FOUND_EMPLOYEES_NUMBER = 2;
		PositionTO savedSeller = departmentService.savePosition(data.getPosById(1));
		DepartmentTO savedDep01 = departmentService.saveDepartment(data.getDepById(0));
		DepartmentTO savedDep02 = departmentService.saveDepartment(data.getDepById(1));
		EmployeeTO savedEmp01 = departmentService.saveEmployee(data.getEmplById(0), savedDep01.getId(), savedSeller.getId());
		EmployeeTO savedEmp02 = departmentService.saveEmployee(data.getEmplById(1), savedDep01.getId(), savedSeller.getId());
		EmployeeTO savedEmp03 = departmentService.saveEmployee(data.getEmplById(2), savedDep01.getId(), savedSeller.getId());
		EmployeeTO savedEmp04 = departmentService.saveEmployee(data.getEmplById(3), savedDep02.getId(), savedSeller.getId());
		CarTO savedCar01 = carService.saveCar(data.getCarById(0));
		CarTO savedCar02 = carService.saveCar(data.getCarById(1));
		CarTO savedCar03 = carService.saveCar(data.getCarById(2));
		carService.addCarer(savedCar01.getId(), savedEmp01.getId());
		carService.addCarer(savedCar01.getId(), savedEmp02.getId());
		carService.addCarer(savedCar02.getId(), savedEmp03.getId());
		carService.addCarer(savedCar03.getId(), savedEmp04.getId());

		// when
		List<EmployeeTO> employeeFromDepartmentWithCars = departmentService.findEmployeesFromDepartmentWithCar(savedDep01.getId(), savedCar01.getId());
		int employeesNo = employeeFromDepartmentWithCars.size();

		// then
		assertEquals(EXPECTED_FOUND_EMPLOYEES_NUMBER, employeesNo);
		assertEquals(savedEmp01.getId(), employeeFromDepartmentWithCars.get(0).getId());
		assertEquals(savedEmp02.getId(), employeeFromDepartmentWithCars.get(1).getId());
	}

	@Test
	@Transactional
	public void shouldFindEmployeeByAllCriteria01() {
		// given
		InsertData data = new InsertData();
		data.initialize();
		
		final int EXPECTED_FOUND_EMPLOYEES_NUMBER = 1;
		PositionTO savedSeller = departmentService.savePosition(data.getPosById(1));
		PositionTO savedManager = departmentService.savePosition(data.getPosById(0));
		DepartmentTO savedDep01 = departmentService.saveDepartment(data.getDepById(0));
		DepartmentTO savedDep02 = departmentService.saveDepartment(data.getDepById(1));
		CarTO savedCar01 = carService.saveCar(data.getCarById(0));
		departmentService.saveEmployee(data.getEmplById(0), savedDep01.getId(), savedSeller.getId());
		EmployeeTO savedEmp02 = departmentService.saveEmployee(data.getEmplById(1), savedDep02.getId(), savedSeller.getId());
		departmentService.saveEmployee(data.getEmplById(2), savedDep01.getId(), savedSeller.getId());
		departmentService.saveEmployee(data.getEmplById(3), savedDep02.getId(), savedManager.getId());
		carService.addCarer(savedCar01.getId(), savedEmp02.getId());
		EmployeeSearchCriteriaTO criteria = new EmployeeSearchCriteriaTO(savedCar01.getId(), savedDep02.getId(), savedSeller.getId());

		// when
		List<EmployeeTO> employeeByCriteria = departmentService.findEmployeesByCriteria(criteria);
		int employeesNo = employeeByCriteria.size();

		// then
		assertEquals(EXPECTED_FOUND_EMPLOYEES_NUMBER, employeesNo);
		assertEquals(savedEmp02.getId(), employeeByCriteria.get(0).getId());
	}

	@Test
	@Transactional
	public void shouldFindEmployeeByAllCriteria02() {
		// given
		InsertData data = new InsertData();
		data.initialize();
		
		final int EXPECTED_FOUND_EMPLOYEES_NUMBER = 2;
		PositionTO savedSeller = departmentService.savePosition(data.getPosById(1));
		PositionTO savedManager = departmentService.savePosition(data.getPosById(0));
		DepartmentTO savedDep01 = departmentService.saveDepartment(data.getDepById(0));
		DepartmentTO savedDep02 = departmentService.saveDepartment(data.getDepById(1));
		CarTO savedCar01 = carService.saveCar(data.getCarById(0));
		CarTO savedCar02 = carService.saveCar(data.getCarById(1));
		EmployeeTO savedEmp01 = departmentService.saveEmployee(data.getEmplById(0), savedDep01.getId(), savedSeller.getId());
		EmployeeTO savedEmp02 = departmentService.saveEmployee(data.getEmplById(1), savedDep01.getId(), savedSeller.getId());
		EmployeeTO savedEmp03 = departmentService.saveEmployee(data.getEmplById(2), savedDep02.getId(), savedSeller.getId());
		departmentService.saveEmployee(data.getEmplById(3), savedDep01.getId(), savedManager.getId());
		EmployeeTO savedEmp05 = departmentService.saveEmployee(data.getEmplById(4), savedDep02.getId(), savedSeller.getId());
		carService.addCarer(savedCar01.getId(), savedEmp01.getId());
		carService.addCarer(savedCar01.getId(), savedEmp02.getId());
		carService.addCarer(savedCar01.getId(), savedEmp03.getId());
		carService.addCarer(savedCar02.getId(), savedEmp05.getId());
		EmployeeSearchCriteriaTO criteria = new EmployeeSearchCriteriaTO(savedCar01.getId(), savedDep01.getId(), savedSeller.getId());

		// when
		List<EmployeeTO> employeeByCriteria = departmentService.findEmployeesByCriteria(criteria);
		int employeesNo = employeeByCriteria.size();

		// then
		assertEquals(EXPECTED_FOUND_EMPLOYEES_NUMBER, employeesNo);
		assertEquals(savedEmp01.getId(), employeeByCriteria.get(0).getId());
		assertEquals(savedEmp02.getId(), employeeByCriteria.get(1).getId());
	}

	@Test
	@Transactional
	public void shouldFindEmployeeByTwoCriteria01() {
		// given
		InsertData data = new InsertData();
		data.initialize();
		
		final int EXPECTED_FOUND_EMPLOYEES_NUMBER = 2;
		PositionTO savedSeller = departmentService.savePosition(data.getPosById(1));
		PositionTO savedManager = departmentService.savePosition(data.getPosById(0));
		DepartmentTO savedDep01 = departmentService.saveDepartment(data.getDepById(0));
		DepartmentTO savedDep02 = departmentService.saveDepartment(data.getDepById(1));
		CarTO savedCar01 = carService.saveCar(data.getCarById(0));
		CarTO savedCar02 = carService.saveCar(data.getCarById(1));
		EmployeeTO savedEmp01 = departmentService.saveEmployee(data.getEmplById(0), savedDep01.getId(), savedSeller.getId());
		EmployeeTO savedEmp02 = departmentService.saveEmployee(data.getEmplById(1), savedDep01.getId(), savedSeller.getId());
		EmployeeTO savedEmp03 = departmentService.saveEmployee(data.getEmplById(2), savedDep02.getId(), savedSeller.getId());
		departmentService.saveEmployee(data.getEmplById(3), savedDep01.getId(), savedManager.getId());
		EmployeeTO savedEmp05 = departmentService.saveEmployee(data.getEmplById(4), savedDep02.getId(), savedSeller.getId());
		carService.addCarer(savedCar01.getId(), savedEmp01.getId());
		carService.addCarer(savedCar01.getId(), savedEmp02.getId());
		carService.addCarer(savedCar01.getId(), savedEmp03.getId());
		carService.addCarer(savedCar02.getId(), savedEmp05.getId());
		EmployeeSearchCriteriaTO criteria = new EmployeeSearchCriteriaTO(null, savedDep02.getId(), savedSeller.getId());

		// when
		List<EmployeeTO> employeeByCriteria = departmentService.findEmployeesByCriteria(criteria);
		int employeesNo = employeeByCriteria.size();

		// then
		assertEquals(EXPECTED_FOUND_EMPLOYEES_NUMBER, employeesNo);
		assertEquals(savedEmp03.getId(), employeeByCriteria.get(0).getId());
		assertEquals(savedEmp05.getId(), employeeByCriteria.get(1).getId());
	}

	@Test
	@Transactional
	public void shouldFindEmployeeByTwoCriteria02() {
		// given
		InsertData data = new InsertData();
		data.initialize();
		
		final int EXPECTED_FOUND_EMPLOYEES_NUMBER = 3;
		PositionTO savedSeller = departmentService.savePosition(data.getPosById(1));
		PositionTO savedManager = departmentService.savePosition(data.getPosById(0));
		DepartmentTO savedDep01 = departmentService.saveDepartment(data.getDepById(0));
		DepartmentTO savedDep02 = departmentService.saveDepartment(data.getDepById(1));
		CarTO savedCar01 = carService.saveCar(data.getCarById(0));
		CarTO savedCar02 = carService.saveCar(data.getCarById(1));
		EmployeeTO savedEmp01 = departmentService.saveEmployee(data.getEmplById(0), savedDep01.getId(), savedSeller.getId());
		EmployeeTO savedEmp02 = departmentService.saveEmployee(data.getEmplById(1), savedDep01.getId(), savedSeller.getId());
		EmployeeTO savedEmp03 = departmentService.saveEmployee(data.getEmplById(2), savedDep02.getId(), savedSeller.getId());
		departmentService.saveEmployee(data.getEmplById(3), savedDep01.getId(), savedManager.getId());
		EmployeeTO savedEmp05 = departmentService.saveEmployee(data.getEmplById(4), savedDep02.getId(), savedSeller.getId());
		carService.addCarer(savedCar01.getId(), savedEmp01.getId());
		carService.addCarer(savedCar02.getId(), savedEmp02.getId());
		carService.addCarer(savedCar02.getId(), savedEmp03.getId());
		carService.addCarer(savedCar02.getId(), savedEmp05.getId());
		EmployeeSearchCriteriaTO criteria = new EmployeeSearchCriteriaTO(savedCar02.getId(), null, savedSeller.getId());

		// when
		List<EmployeeTO> employeeByCriteria = departmentService.findEmployeesByCriteria(criteria);
		int employeesNo = employeeByCriteria.size();

		// then
		assertEquals(EXPECTED_FOUND_EMPLOYEES_NUMBER, employeesNo);
		assertEquals(savedEmp02.getId(), employeeByCriteria.get(0).getId());
		assertEquals(savedEmp03.getId(), employeeByCriteria.get(1).getId());
		assertEquals(savedEmp05.getId(), employeeByCriteria.get(2).getId());
	}

	@Test
	@Transactional
	public void shouldFindEmployeeByTwoCriteria03() {
		// given
		InsertData data = new InsertData();
		data.initialize();
		
		final int EXPECTED_FOUND_EMPLOYEES_NUMBER = 2;
		PositionTO savedSeller = departmentService.savePosition(data.getPosById(1));
		PositionTO savedManager = departmentService.savePosition(data.getPosById(0));
		DepartmentTO savedDep01 = departmentService.saveDepartment(data.getDepById(0));
		DepartmentTO savedDep02 = departmentService.saveDepartment(data.getDepById(1));
		CarTO savedCar01 = carService.saveCar(data.getCarById(0));
		CarTO savedCar02 = carService.saveCar(data.getCarById(1));
		EmployeeTO savedEmp01 = departmentService.saveEmployee(data.getEmplById(0), savedDep01.getId(), savedSeller.getId());
		EmployeeTO savedEmp02 = departmentService.saveEmployee(data.getEmplById(1), savedDep01.getId(), savedSeller.getId());
		EmployeeTO savedEmp03 = departmentService.saveEmployee(data.getEmplById(2), savedDep02.getId(), savedSeller.getId());
		departmentService.saveEmployee(data.getEmplById(3), savedDep02.getId(), savedManager.getId());
		EmployeeTO savedEmp05 = departmentService.saveEmployee(data.getEmplById(4), savedDep02.getId(), savedManager.getId());
		carService.addCarer(savedCar01.getId(), savedEmp01.getId());
		carService.addCarer(savedCar02.getId(), savedEmp02.getId());
		carService.addCarer(savedCar02.getId(), savedEmp03.getId());
		carService.addCarer(savedCar02.getId(), savedEmp05.getId());
		EmployeeSearchCriteriaTO criteria = new EmployeeSearchCriteriaTO(savedCar02.getId(), savedDep02.getId(), null);

		// when
		List<EmployeeTO> employeeByCriteria = departmentService.findEmployeesByCriteria(criteria);
		int employeesNo = employeeByCriteria.size();

		// then
		assertEquals(EXPECTED_FOUND_EMPLOYEES_NUMBER, employeesNo);
		assertEquals(savedEmp03.getId(), employeeByCriteria.get(0).getId());
		assertEquals(savedEmp05.getId(), employeeByCriteria.get(1).getId());
	}

	@Test
	@Transactional
	public void shouldFindEmployeeByOneCriteria01() {
		// given
		InsertData data = new InsertData();
		data.initialize();
		
		final int EXPECTED_FOUND_EMPLOYEES_NUMBER = 3;
		PositionTO savedSeller = departmentService.savePosition(data.getPosById(1));
		PositionTO savedManager = departmentService.savePosition(data.getPosById(0));
		DepartmentTO savedDep01 = departmentService.saveDepartment(data.getDepById(0));
		DepartmentTO savedDep02 = departmentService.saveDepartment(data.getDepById(1));
		CarTO savedCar01 = carService.saveCar(data.getCarById(0));
		CarTO savedCar02 = carService.saveCar(data.getCarById(1));
		EmployeeTO savedEmp01 = departmentService.saveEmployee(data.getEmplById(0), savedDep01.getId(), savedSeller.getId());
		EmployeeTO savedEmp02 = departmentService.saveEmployee(data.getEmplById(1), savedDep01.getId(), savedSeller.getId());
		EmployeeTO savedEmp03 = departmentService.saveEmployee(data.getEmplById(2), savedDep02.getId(), savedSeller.getId());
		departmentService.saveEmployee(data.getEmplById(3), savedDep02.getId(), savedManager.getId());
		EmployeeTO savedEmp05 = departmentService.saveEmployee(data.getEmplById(4), savedDep02.getId(), savedManager.getId());
		carService.addCarer(savedCar01.getId(), savedEmp01.getId());
		carService.addCarer(savedCar02.getId(), savedEmp02.getId());
		carService.addCarer(savedCar02.getId(), savedEmp03.getId());
		carService.addCarer(savedCar02.getId(), savedEmp05.getId());
		EmployeeSearchCriteriaTO criteria = new EmployeeSearchCriteriaTO(savedCar02.getId(), null, null);

		// when
		List<EmployeeTO> employeeByCriteria = departmentService.findEmployeesByCriteria(criteria);
		int employeesNo = employeeByCriteria.size();

		// then
		assertEquals(EXPECTED_FOUND_EMPLOYEES_NUMBER, employeesNo);
		assertEquals(savedEmp02.getId(), employeeByCriteria.get(0).getId());
		assertEquals(savedEmp03.getId(), employeeByCriteria.get(1).getId());
		assertEquals(savedEmp05.getId(), employeeByCriteria.get(2).getId());
	}

	@Test
	@Transactional
	public void shouldFindEmployeeByOneCriteria02() {
		// given
		InsertData data = new InsertData();
		data.initialize();
		
		final int EXPECTED_FOUND_EMPLOYEES_NUMBER = 3;
		PositionTO savedSeller = departmentService.savePosition(data.getPosById(1));
		PositionTO savedManager = departmentService.savePosition(data.getPosById(0));
		DepartmentTO savedDep01 = departmentService.saveDepartment(data.getDepById(0));
		DepartmentTO savedDep02 = departmentService.saveDepartment(data.getDepById(1));
		CarTO savedCar01 = carService.saveCar(data.getCarById(0));
		CarTO savedCar02 = carService.saveCar(data.getCarById(1));
		EmployeeTO savedEmp01 = departmentService.saveEmployee(data.getEmplById(0), savedDep01.getId(), savedSeller.getId());
		departmentService.saveEmployee(data.getEmplById(1), savedDep01.getId(), savedSeller.getId());
		EmployeeTO savedEmp03 = departmentService.saveEmployee(data.getEmplById(2), savedDep02.getId(), savedSeller.getId());
		EmployeeTO savedEmp04 = departmentService.saveEmployee(data.getEmplById(3), savedDep02.getId(), savedManager.getId());
		EmployeeTO savedEmp05 = departmentService.saveEmployee(data.getEmplById(4), savedDep02.getId(), savedManager.getId());
		carService.addCarer(savedCar01.getId(), savedEmp01.getId());
		carService.addCarer(savedCar01.getId(), savedEmp03.getId());
		carService.addCarer(savedCar02.getId(), savedEmp04.getId());
		carService.addCarer(savedCar02.getId(), savedEmp05.getId());
		EmployeeSearchCriteriaTO criteria = new EmployeeSearchCriteriaTO(null, savedDep02.getId(), null);

		// when
		List<EmployeeTO> employeeByCriteria = departmentService.findEmployeesByCriteria(criteria);
		int employeesNo = employeeByCriteria.size();

		// then
		assertEquals(EXPECTED_FOUND_EMPLOYEES_NUMBER, employeesNo);
		assertEquals(savedEmp03.getId(), employeeByCriteria.get(0).getId());
		assertEquals(savedEmp04.getId(), employeeByCriteria.get(1).getId());
		assertEquals(savedEmp05.getId(), employeeByCriteria.get(2).getId());
	}

	@Test
	@Transactional
	public void shouldFindEmployeeByOneCriteria03() {
		// given
		InsertData data = new InsertData();
		data.initialize();
		
		final int EXPECTED_FOUND_EMPLOYEES_NUMBER = 2;
		PositionTO savedSeller = departmentService.savePosition(data.getPosById(1));
		PositionTO savedManager = departmentService.savePosition(data.getPosById(0));
		DepartmentTO savedDep01 = departmentService.saveDepartment(data.getDepById(0));
		DepartmentTO savedDep02 = departmentService.saveDepartment(data.getDepById(1));
		CarTO savedCar01 = carService.saveCar(data.getCarById(0));
		CarTO savedCar02 = carService.saveCar(data.getCarById(1));
		EmployeeTO savedEmp01 = departmentService.saveEmployee(data.getEmplById(0), savedDep01.getId(), savedSeller.getId());
		EmployeeTO savedEmp02 = departmentService.saveEmployee(data.getEmplById(1), savedDep01.getId(), savedSeller.getId());
		EmployeeTO savedEmp03 = departmentService.saveEmployee(data.getEmplById(2), savedDep02.getId(), savedSeller.getId());
		EmployeeTO savedEmp04 = departmentService.saveEmployee(data.getEmplById(3), savedDep01.getId(), savedManager.getId());
		EmployeeTO savedEmp05 = departmentService.saveEmployee(data.getEmplById(4), savedDep02.getId(), savedManager.getId());
		carService.addCarer(savedCar01.getId(), savedEmp01.getId());
		carService.addCarer(savedCar01.getId(), savedEmp03.getId());
		carService.addCarer(savedCar02.getId(), savedEmp02.getId());
		carService.addCarer(savedCar02.getId(), savedEmp03.getId());
		EmployeeSearchCriteriaTO criteria = new EmployeeSearchCriteriaTO(null, null, savedManager.getId());

		// when
		List<EmployeeTO> employeeByCriteria = departmentService.findEmployeesByCriteria(criteria);
		int employeesNo = employeeByCriteria.size();

		// then
		assertEquals(EXPECTED_FOUND_EMPLOYEES_NUMBER, employeesNo);
		assertEquals(savedEmp04.getId(), employeeByCriteria.get(0).getId());
		assertEquals(savedEmp05.getId(), employeeByCriteria.get(1).getId());
	}

	@Test
	@Transactional
	public void shouldFindEmployeeByWithoutCriteria() {
		// given
		InsertData data = new InsertData();
		data.initialize();
		
		final int EXPECTED_FOUND_EMPLOYEES_NUMBER = 0;
		PositionTO savedSeller = departmentService.savePosition(data.getPosById(1));
		PositionTO savedManager = departmentService.savePosition(data.getPosById(0));
		DepartmentTO savedDep01 = departmentService.saveDepartment(data.getDepById(0));
		DepartmentTO savedDep02 = departmentService.saveDepartment(data.getDepById(1));
		CarTO savedCar01 = carService.saveCar(data.getCarById(0));
		CarTO savedCar02 = carService.saveCar(data.getCarById(1));
		EmployeeTO savedEmp01 = departmentService.saveEmployee(data.getEmplById(0), savedDep01.getId(), savedSeller.getId());
		EmployeeTO savedEmp02 = departmentService.saveEmployee(data.getEmplById(1), savedDep01.getId(), savedSeller.getId());
		EmployeeTO savedEmp03 = departmentService.saveEmployee(data.getEmplById(2), savedDep02.getId(), savedSeller.getId());
		departmentService.saveEmployee(data.getEmplById(3), savedDep01.getId(), savedManager.getId());
		departmentService.saveEmployee(data.getEmplById(4), savedDep02.getId(), savedManager.getId());
		carService.addCarer(savedCar01.getId(), savedEmp01.getId());
		carService.addCarer(savedCar01.getId(), savedEmp03.getId());
		carService.addCarer(savedCar02.getId(), savedEmp02.getId());
		carService.addCarer(savedCar02.getId(), savedEmp03.getId());
		EmployeeSearchCriteriaTO criteria = new EmployeeSearchCriteriaTO(null, null, null);

		// when
		List<EmployeeTO> employeeByCriteria = departmentService.findEmployeesByCriteria(criteria);
		int employeesNo = employeeByCriteria.size();

		// then
		assertEquals(EXPECTED_FOUND_EMPLOYEES_NUMBER, employeesNo);
	}

}
