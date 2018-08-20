package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.types.EmployeeSearchCriteriaTO;

public interface EmployeeDao extends Dao<EmployeeEntity, Long> {

	List<EmployeeEntity> findEmployeesByCar(Long id);
	List<EmployeeEntity> findEmployeesByCriteria(EmployeeSearchCriteriaTO criteria);
	List<EmployeeEntity> findEmployeesFromDepartmentWithCar(Long idDepartment, Long idCar);

}
