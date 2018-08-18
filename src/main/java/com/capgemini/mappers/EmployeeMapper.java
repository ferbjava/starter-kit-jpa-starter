package com.capgemini.mappers;

import java.util.Calendar;
import java.util.Set;
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
		Set<CarTO> cars = CarMapper.map2TOs(entity.getCars());
		return new EmployeeTOBuilder().withId(id).withFirstName(firstName).withLastName(lastName)
				.withDateBirth(dateBirth).withCars(cars).build();
	}

	public static EmployeeEntity toEmployeeEntity(EmployeeTO employeeTO) {
		if (employeeTO == null) {
			return null;
		}
		String firstName = employeeTO.getFirstName();
		String lastName = employeeTO.getLastName();
		Calendar dateBirth = employeeTO.getDateBirth();
		Set<CarEntity> cars = CarMapper.map2Entities(employeeTO.getCars());
		return new EmployeeEntity(firstName, lastName, dateBirth, cars);
	}

	public static Set<EmployeeTO> map2TOs(Set<EmployeeEntity> entities) {
		if (entities == null) {
			return null;
		}
		return entities.stream().map(EmployeeMapper::toEmployeeTO).collect(Collectors.toSet());
	}

	public static Set<EmployeeEntity> map2Entities(Set<EmployeeTO> employeeTOs) {
		if (employeeTOs == null) {
			return null;
		}
		return employeeTOs.stream().map(EmployeeMapper::toEmployeeEntity).collect(Collectors.toSet());
	}

}
