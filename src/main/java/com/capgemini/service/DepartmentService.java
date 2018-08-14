package com.capgemini.service;

import com.capgemini.types.EmployeeTO;
import com.capgemini.types.PositionTO;

public interface DepartmentService {

	PositionTO savePosition(PositionTO position);
	PositionTO findPositionById(Long id);
	
	EmployeeTO saveEmployee(EmployeeTO employee);
	EmployeeTO findEmployeeById(Long id);
	
	long findEmployeesNo();
	long findPositionsNo();
	
	void deletePosition(Long id);
	void deleteEmployee(Long id);
	
}
