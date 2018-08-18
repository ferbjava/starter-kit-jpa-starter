package com.capgemini.mappers;

import java.util.Set;

import com.capgemini.domain.DepartmentEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.types.DepartmentTO;
import com.capgemini.types.DepartmentTO.DepartmentTOBuilder;
import com.capgemini.types.EmployeeTO;

public class DepartmentMapper {

	public static DepartmentTO toDepartmentTO(DepartmentEntity entity) {
		if (entity == null) {
			return null;
		}
		Long id = entity.getId();
		String adress = entity.getAdress();
		Integer phoneNumber = entity.getPhoneNumber();
		String email = entity.getEmail();
		Set<EmployeeTO> employees = EmployeeMapper.map2TOs(entity.getEmployees());
		return new DepartmentTOBuilder().withId(id).withAdress(adress).withPhoneNumber(phoneNumber).withEmail(email)
				.withEmployees(employees).build();
	}

	public static DepartmentEntity toDepartmentEntity(DepartmentTO departmentTO) {
		if (departmentTO == null) {
			return null;
		}
		String adress = departmentTO.getAdress();
		Integer phoneNumber = departmentTO.getPhoneNumber();
		String email = departmentTO.getEmail();
		Set<EmployeeEntity> employees = EmployeeMapper.map2Entities(departmentTO.getEmployees());
		return new DepartmentEntity(adress, phoneNumber, email, employees);
	}

}
