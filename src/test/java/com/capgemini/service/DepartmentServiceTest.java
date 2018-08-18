package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.GregorianCalendar;

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
@SpringBootTest(properties = "spring.profiles.active=hsql")
public class DepartmentServiceTest {

	@Autowired
	private DepartmentService departmentService;

	@Test
	@Transactional
	public void shouldFindEmployeeById() {
		// given
		PositionTO pos01 = new PositionTOBuilder().withPosition("manager").build();
		PositionTO savedPos1 = departmentService.savePosition(pos01);
		PositionTO pos02 = new PositionTOBuilder().withPosition("dealer").build();
		PositionTO savedPos2 = departmentService.savePosition(pos02);
		PositionTO pos03 = new PositionTOBuilder().withPosition("accountant").build();
		PositionTO savedPos3 = departmentService.savePosition(pos03);

		EmployeeTO emp01 = new EmployeeTOBuilder().withFirstName("Zenon").withLastName("Martyniuk")
				.withDateBirth(new GregorianCalendar(1970, 01, 20)).build();
		EmployeeTO savedEmp01 = departmentService.saveEmployee(emp01,null,savedPos2.getId());

		// when
		EmployeeTO selectedEmp01 = departmentService.findEmployeeById(savedEmp01.getId());

		// then
		assertNotNull(selectedEmp01);
		assertEquals(savedEmp01.getFirstName(), selectedEmp01.getFirstName());
		assertEquals(savedEmp01.getId(), selectedEmp01.getId());

	}

//	@Test
//	@Transactional
//	public void shouldRemoveEmployeeById() {
//		// given
//		PositionTO manager = new PositionTOBuilder().withPosition("manager").build();
//		PositionTO savedPos1 = departmentService.savePosition(manager);
//		PositionTO dealer = new PositionTOBuilder().withPosition("dealer").build();
//		PositionTO savedPos2 = departmentService.savePosition(dealer);
//		PositionTO accountant = new PositionTOBuilder().withPosition("accountant").build();
//		PositionTO savedPos3 = departmentService.savePosition(accountant);
//
//		Long noPosition = departmentService.findPositionsNo();
//		System.out.println("Number of positions: " + noPosition);
//
//		EmployeeTO emp01 = new EmployeeTOBuilder().withFirstName("Zenon").withLastName("Martyniuk")
//				.withDateBirth(new GregorianCalendar(1970, 01, 20)).build();
//
//		EmployeeTO savedEmp01 = departmentService.saveEmployee(emp01);
//		Long noEmp = departmentService.findEmployeesNo();
//		System.out.println("Number of employees: " + noEmp);
//		EmployeeTO selectedEmp01 = departmentService.findEmployeeById(savedEmp01.getId());
//
//		// when
//		departmentService.deleteEmployee(selectedEmp01.getId());
//		noPosition = departmentService.findPositionsNo();
//		System.out.println("Number of positions: " + noPosition);
//		noEmp = departmentService.findEmployeesNo();
//		System.out.println("Number of employees: " + noEmp);
//
//		// then
//		assertNotNull(selectedEmp01);
//		assertEquals(savedEmp01.getFirstName(), selectedEmp01.getFirstName());
//		assertEquals(savedEmp01.getId(), selectedEmp01.getId());
//
//	}
//
//	@Test
//	@Transactional
//	public void shouldRemovePositionById() {
//		// given
//		PositionTO manager = new PositionTOBuilder().withPosition("manager").build();
//		PositionTO savedPos1 = departmentService.savePosition(manager);
//		PositionTO dealer = new PositionTOBuilder().withPosition("dealer").build();
//		PositionTO savedPos2 = departmentService.savePosition(dealer);
//		PositionTO accountant = new PositionTOBuilder().withPosition("accountant").build();
//		PositionTO savedPos3 = departmentService.savePosition(accountant);
//		PositionTO selectedPos01 = departmentService.findPositionById(savedPos1.getId());
//
//		Long noPosition = departmentService.findPositionsNo();
//		System.out.println("Number of positions: " + noPosition);
//		System.out.println("Position 2 details: " + savedPos2.toString());
//
//		EmployeeTO emp01 = new EmployeeTOBuilder().withFirstName("Zenon").withLastName("Martyniuk")
//				.withDateBirth(new GregorianCalendar(1970, 01, 20)).build();
//		EmployeeTO savedEmp01 = departmentService.saveEmployee(emp01);
//		EmployeeTO selectedEmp01 = departmentService.findEmployeeById(savedEmp01.getId());
//
//		EmployeeTO emp02 = new EmployeeTOBuilder().withFirstName("Adam").withLastName("Nowak")
//				.withDateBirth(new GregorianCalendar(1970, 12, 20)).build();
//		EmployeeTO savedEmp02 = departmentService.saveEmployee(emp02);
//		EmployeeTO selectedEmp02 = departmentService.findEmployeeById(savedEmp02.getId());
//
//		EmployeeTO emp03 = new EmployeeTOBuilder().withFirstName("Jan").withLastName("Kowalski")
//				.withDateBirth(new GregorianCalendar(1980, 01, 20)).build();
//		EmployeeTO savedEmp03 = departmentService.saveEmployee(emp03);
//		EmployeeTO selectedEmp03 = departmentService.findEmployeeById(savedEmp03.getId());
//
//		System.out.println("-----Test5----------");
//		Long noEmp = departmentService.findEmployeesNo();
//		System.out.println("Number of employees: " + noEmp);
//		System.out.println("Employee data: " + selectedEmp01.toString());
//		System.out.println("Employee data: " + selectedEmp02.toString());
//		System.out.println("Employee data: " + selectedEmp03.toString());
//		noPosition = departmentService.findPositionsNo();
//		System.out.println("Number of positions: " + noPosition);
//
//		// when
//		System.out.println("-----Test6----------");
//		departmentService.deletePosition(savedPos2.getId());
//		System.out.println("Position 2 deleted");
//		PositionTO pos = departmentService.findPositionById(savedPos2.getId());
//		EmployeeTO selectedEmp03New = departmentService.findEmployeeById(savedEmp01.getId());
//		
//		
//
//		noEmp = departmentService.findEmployeesNo();
//		System.out.println("Number of employees: " + noEmp);
//
//		noPosition = departmentService.findPositionsNo();
//		System.out.println("-----Test7----------");
//		System.out.println("Number of positions: " + noPosition);
//
//		noEmp = departmentService.findEmployeesNo();
//		System.out.println("Number of employees: " + noEmp);
//		selectedEmp01 = departmentService.findEmployeeById(savedEmp01.getId());
//		System.out.println("Employee data: " + selectedEmp01.toString());
//
//		// then
//		assertNotNull(selectedEmp01);
//		assertEquals(savedPos1.getPosition(), selectedPos01.getPosition());
//		assertEquals(savedPos1.getId(), selectedPos01.getId());
//
//	}

}
