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

import com.capgemini.Utils.EmployeeSearchCriteria;
import com.capgemini.Utils.InsertData;
import com.capgemini.types.DepartmentTO;
import com.capgemini.types.DepartmentTO.DepartmentTOBuilder;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.PositionTO;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
public class DepartmentServiceTest {

	@Autowired
	private DepartmentService departmentService;

	@Test
	@Transactional
	public void shouldAddPositions() {
		// given
		InsertData data = new InsertData();
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
		final long EXPECTED_INITIAL_POSITIONS_NUMBER = 3;
		final long EXPECTED_FINAL_POSITIONS_NUMBER = 3;
		final long EXPECTED_INITIAL_EMPLOYEES_NUMBER = 1;
		final long EXPECTED_FINAL_EMPLOYEES_NUMBER = 0;
		departmentService.savePosition(data.getPosById(0));
		PositionTO savedPos2 = departmentService.savePosition(data.getPosById(1));
		departmentService.savePosition(data.getPosById(2));
		EmployeeTO savedEmp01 = departmentService.saveEmployee(data.getEmplById(0), null, savedPos2.getId());

		// when
		long initialPositionsNumber = departmentService.findPositionsNo();
		long initialEmployeesNumber = departmentService.findEmployeesNo();
		departmentService.deleteEmployee(savedEmp01.getId());
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
	public void shouldAddDepartments() {
		// given
		InsertData data = new InsertData();
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
	public void shouldFindEmployeeFromDepartment() {
		// given
		InsertData data = new InsertData();
		final int EXPECTED_FOUND_EMPLOYEES_NUMBER = 2;
		DepartmentTO savedDep01 = departmentService.saveDepartment(data.getDepById(0));
		DepartmentTO savedDep02 = departmentService.saveDepartment(data.getDepById(1));
		EmployeeTO savedEmp01 = departmentService.saveEmployee(data.getEmplById(0), savedDep01.getId(), null);
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
	public void shouldFindEmployeeByCriteria() {
		// given
		InsertData data = new InsertData();
		final int EXPECTED_FOUND_EMPLOYEES_NUMBER = 2;
		DepartmentTO savedDep01 = departmentService.saveDepartment(data.getDepById(0));
		DepartmentTO savedDep02 = departmentService.saveDepartment(data.getDepById(1));
		EmployeeTO savedEmp01 = departmentService.saveEmployee(data.getEmplById(0), savedDep01.getId(), null);
		EmployeeTO savedEmp02 = departmentService.saveEmployee(data.getEmplById(1), savedDep02.getId(), null);
		EmployeeTO savedEmp03 = departmentService.saveEmployee(data.getEmplById(2), savedDep02.getId(), null);
		EmployeeSearchCriteria criteria = new EmployeeSearchCriteria(null, savedDep02.getId(), null);

		// when
		List<EmployeeTO> employeeByCriteria = departmentService.findEmployeeByCriteria(criteria);
		int employeesNo = employeeByCriteria.size();

		// then
		assertEquals(EXPECTED_FOUND_EMPLOYEES_NUMBER, employeesNo);
		assertEquals(savedEmp02.getId(), employeeByCriteria.get(0).getId());
		assertEquals(savedEmp03.getId(), employeeByCriteria.get(1).getId());
	}

}


//"SELECT e FROM DepartmentEntity d INNER JOIN d.employees e WHERE d.id = :id"
//"SELECT e FROM DepartmentEntity d INNER JOIN d.employees e WHERE d.id = :id"
//
//"SELECT e FROM EmployeesEntity e INNER JOIN DepartmentEntity d INNER JOIN PositionEntity p INNER JOIN CarEntity c "
//+ "WHERE d.id = :depId AND p.id = :posId AND c.id = :carId"