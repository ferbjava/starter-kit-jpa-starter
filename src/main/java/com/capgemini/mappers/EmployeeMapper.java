package com.capgemini.mappers;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.types.EmployeeTO;

public class EmployeeMapper {

	public static EmployeeTO toEmployeeTO(EmployeeEntity entity){
		EmployeeTO employeeTO = new EmployeeTO();
		employeeTO.setFirstName(entity.getFirstName());
		employeeTO.setLastName(entity.getLastName());
		employeeTO.setDateBirth(entity.getDateBirth());
		employeeTO.setPosition(PositionMapper.toPositionTO(entity.getPosition()));
		return employeeTO;
	}
	
	public static EmployeeEntity toEmployeeEntity(EmployeeTO employeeTO){
		EmployeeEntity entity = new EmployeeEntity();
		entity.setFirstName(employeeTO.getFirstName());
		entity.setLastName(employeeTO.getLastName());
		entity.setDateBirth(employeeTO.getDateBirth());
		entity.setPosition(PositionMapper.toPositionEntity(employeeTO.getPosition()));
		return entity;
	}
	
}
