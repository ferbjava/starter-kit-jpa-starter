package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.Utils.InsertData;
import com.capgemini.domain.PositionEntity;
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
		long EXPECTED_INITIAL_POSITIONS_NUMBER = 0;
		long EXPECTED_FINAL_POSITIONS_NUMBER = 3;

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
		long EXPECTED_INITIAL_EMPLOYEES_NUMBER = 0;
		long EXPECTED_FINAL_EMPLOYEES_NUMBER = 1;
		PositionTO savedPos1 = departmentService.savePosition(data.getPosById(0));
		PositionTO savedPos2 = departmentService.savePosition(data.getPosById(1));
		PositionTO savedPos3 = departmentService.savePosition(data.getPosById(2));

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
		PositionTO savedPos1 = departmentService.savePosition(data.getPosById(0));
		PositionTO savedPos2 = departmentService.savePosition(data.getPosById(1));
		PositionTO savedPos3 = departmentService.savePosition(data.getPosById(2));
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
		long EXPECTED_INITIAL_POSITIONS_NUMBER = 3;
		long EXPECTED_FINAL_POSITIONS_NUMBER = 2;
		long EXPECTED_INITIAL_EMPLOYEES_NUMBER = 1;
		long EXPECTED_FINAL_EMPLOYEES_NUMBER = 0;
		PositionTO savedPos1 = departmentService.savePosition(data.getPosById(0));
		PositionTO savedPos2 = departmentService.savePosition(data.getPosById(1));
		PositionTO savedPos3 = departmentService.savePosition(data.getPosById(2));
		EmployeeTO savedEmp01 = departmentService.saveEmployee(data.getEmplById(0), null, savedPos2.getId());

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
		long EXPECTED_INITIAL_POSITIONS_NUMBER = 3;
		long EXPECTED_FINAL_POSITIONS_NUMBER = 3;
		long EXPECTED_INITIAL_EMPLOYEES_NUMBER = 1;
		long EXPECTED_FINAL_EMPLOYEES_NUMBER = 0;
		PositionTO savedPos1 = departmentService.savePosition(data.getPosById(0));
		PositionTO savedPos2 = departmentService.savePosition(data.getPosById(1));
		PositionTO savedPos3 = departmentService.savePosition(data.getPosById(2));
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

}
