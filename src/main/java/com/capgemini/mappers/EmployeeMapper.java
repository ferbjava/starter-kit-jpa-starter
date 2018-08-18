package com.capgemini.mappers;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.EmployeeTO.EmployeeTOBuilder;

public class EmployeeMapper {

	public static EmployeeTO toEmployeeTO(EmployeeEntity entity) {
		if (entity == null) {
			return null;
		}
		Long id = entity.getId();
		String firstName = entity.getFirstName();
		String lastName = entity.getLastName();
		Calendar dateBirth = entity.getDateBirth();
		List<CarTO> cars = CarMapper.map2TOs(entity.getCars());
		return new EmployeeTOBuilder().withId(id).withFirstName(firstName).withLastName(lastName)
				.withDateBirth(dateBirth).withCars(cars).build();
	}

	public static EmployeeEntity toEmployeeEntity(EmployeeTO employeeTO) {
		if (employeeTO == null) {
			return null;
		}
		Long id = employeeTO.getId();
		String firstName = employeeTO.getFirstName();
		String lastName = employeeTO.getLastName();
		Calendar dateBirth = employeeTO.getDateBirth();
		List<CarEntity> cars = CarMapper.map2Entities(employeeTO.getCars());
		return new EmployeeEntity(id, firstName, lastName, dateBirth, cars);
	}

	public static List<EmployeeTO> map2TOs(List<EmployeeEntity> entities) {
		if (entities == null) {
			return null;
		}
		return entities.stream().map(EmployeeMapper::toEmployeeTO).collect(Collectors.toList());
	}

	public static List<EmployeeEntity> map2Entities(List<EmployeeTO> employeeTOs) {
		if (employeeTOs == null) {
			return null;
		}
		return employeeTOs.stream().map(EmployeeMapper::toEmployeeEntity).collect(Collectors.toList());
	}

}
