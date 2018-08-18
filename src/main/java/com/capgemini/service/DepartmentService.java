package com.capgemini.service;

import com.capgemini.types.DepartmentTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.PositionTO;

public interface DepartmentService {
	
	DepartmentTO saveDepartment(DepartmentTO department);
	DepartmentTO findDepartmentById(Long id);
	DepartmentTO updateDepartment(DepartmentTO department);
	
	PositionTO savePosition(PositionTO position);
	PositionTO findPositionById(Long id);
	
	EmployeeTO saveEmployee(EmployeeTO employee, Long idDep, Long idPos);
	EmployeeTO findEmployeeById(Long id);
	
	long findDepartmentNo();
	long findEmployeesNo();
	long findPositionsNo();
	
	void deleteDepartment(Long id);
	void deletePosition(Long id);
	void deleteEmployee(Long id);
	
}
