package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.types.EmployeeTO;
import com.capgemini.types.EmployeeTO.EmployeeTOBuilder;
import com.capgemini.types.PositionTO;
import com.capgemini.types.PositionTO.PositionTOBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest

public class DepartmentServiceTest {

	@Autowired
	private DepartmentService departmentService;

	@Test
	@Transactional
	public void shouldFindEmployeeById() {
		// given
		PositionTO manager = new PositionTOBuilder().withPosition("manager").build();
		PositionTO savedPos1 = departmentService.savePosition(manager);
		PositionTO dealer = new PositionTOBuilder().withPosition("dealer").build();
		PositionTO savedPos2 = departmentService.savePosition(dealer);
		PositionTO accountant = new PositionTOBuilder().withPosition("accountant").build();
		PositionTO savedPos3 = departmentService.savePosition(accountant);

		EmployeeTO emp01 = new EmployeeTOBuilder().withFirstName("Zenon").withLastName("Martyniuk")
				.withDateBirth(new Date(1970, 12, 01)).withPosition(savedPos2).build();
		EmployeeTO savedEmp01 = departmentService.saveEmployee(emp01);

		// when
		EmployeeTO selectedEmp01 = departmentService.findEmployeeById(savedEmp01.getId());

		// then
		assertNotNull(selectedEmp01);
		assertEquals(savedEmp01.getFirstName(), selectedEmp01.getFirstName());
		assertEquals(savedEmp01.getId(), selectedEmp01.getId());

	}

	@Test
	@Transactional
	public void shouldRemoveEmployeeById() {
		// given
		PositionTO manager = new PositionTOBuilder().withPosition("manager").build();
		PositionTO savedPos1 = departmentService.savePosition(manager);
		PositionTO dealer = new PositionTOBuilder().withPosition("dealer").build();
		PositionTO savedPos2 = departmentService.savePosition(dealer);
		PositionTO accountant = new PositionTOBuilder().withPosition("accountant").build();
		PositionTO savedPos3 = departmentService.savePosition(accountant);
		
		Long noPosition = departmentService.findPositionsNo();
		System.out.println("Number of positions: "+noPosition);

		EmployeeTO emp01 = new EmployeeTOBuilder().withFirstName("Zenon").withLastName("Martyniuk")
				.withDateBirth(new Date(1970, 12, 01)).withPosition(savedPos2).build();
		
		EmployeeTO savedEmp01 = departmentService.saveEmployee(emp01);
		Long noEmp = departmentService.findEmployeesNo();
		System.out.println("Number of employees: "+noEmp);
		EmployeeTO selectedEmp01 = departmentService.findEmployeeById(savedEmp01.getId());

		// when
		departmentService.deleteEmployee(selectedEmp01.getId());
		noPosition = departmentService.findPositionsNo();
		System.out.println("Number of positions: "+noPosition);
		noEmp = departmentService.findEmployeesNo();
		System.out.println("Number of employees: "+noEmp);

		// then
		assertNotNull(selectedEmp01);
		assertEquals(savedEmp01.getFirstName(), selectedEmp01.getFirstName());
		assertEquals(savedEmp01.getId(), selectedEmp01.getId());

	}

}
