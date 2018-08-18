package com.capgemini.mappers;

import java.util.List;

import com.capgemini.domain.DepartmentEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.RentalEntity;
import com.capgemini.types.DepartmentTO;
import com.capgemini.types.DepartmentTO.DepartmentTOBuilder;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.RentalTO;

public class DepartmentMapper {

	public static DepartmentTO toDepartmentTO(DepartmentEntity entity) {
		if (entity == null) {
			return null;
		}
		Long id = entity.getId();
		String adress = entity.getAdress();
		Integer phoneNumber = entity.getPhoneNumber();
		String email = entity.getEmail();
		List<EmployeeTO> employees = EmployeeMapper.map2TOs(entity.getEmployees());
		List<RentalTO> rentalsStart = RentalMapper.map2TOs(entity.getRentalsStart());
		List<RentalTO> rentalsStop = RentalMapper.map2TOs(entity.getRentalsStop());
		return new DepartmentTOBuilder().withId(id).withAdress(adress).withPhoneNumber(phoneNumber).withEmail(email)
				.withEmployees(employees).withRentalsStart(rentalsStart).withRentalsStop(rentalsStop).build();
	}

	public static DepartmentEntity toDepartmentEntity(DepartmentTO departmentTO) {
		if (departmentTO == null) {
			return null;
		}
		Long id = departmentTO.getId();
		String adress = departmentTO.getAdress();
		Integer phoneNumber = departmentTO.getPhoneNumber();
		String email = departmentTO.getEmail();
		List<EmployeeEntity> employees = EmployeeMapper.map2Entities(departmentTO.getEmployees());
		List<RentalEntity> rentalsStart = RentalMapper.map2Entities(departmentTO.getRentalsStart());
		List<RentalEntity> rentalsStop = RentalMapper.map2Entities(departmentTO.getRentalsStop());
		return new DepartmentEntity(id, adress, phoneNumber, email, employees, rentalsStart, rentalsStop);
	}

}
