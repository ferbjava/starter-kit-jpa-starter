package com.capgemini.mappers;

import java.util.Date;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.PositionEntity;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.EmployeeTO.EmployeeTOBuilder;
import com.capgemini.types.PositionTO;

public class EmployeeMapper {

	public static EmployeeTO toEmployeeTO(EmployeeEntity entity) {
		Long id = entity.getId();
		String firstName = entity.getFirstName();
		String lastName = entity.getLastName();
		Date dateBirth = entity.getDateBirth();
		PositionTO position = PositionMapper.toPositionTO(entity.getPosition());
		return new EmployeeTOBuilder().withId(id).withFirstName(firstName).withLastName(lastName)
				.withDateBirth(dateBirth).withPosition(position).build();
	}

	public static EmployeeEntity toEmployeeEntity(EmployeeTO employeeTO) {
		String firstName = employeeTO.getFirstName();
		String lastName = employeeTO.getLastName();
		Date dateBirth = employeeTO.getDateBirth();
		PositionEntity position = PositionMapper.toPositionEntity(employeeTO.getPosition());
		return new EmployeeEntity(firstName, lastName, dateBirth, position);
	}

}
