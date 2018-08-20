package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.DepartmentEntity;
import com.capgemini.domain.EmployeeEntity;

public interface DepartmentDao extends Dao<DepartmentEntity, Long> {

	DepartmentEntity findDepartmentByEmployeeId(Long id);
	List<EmployeeEntity> findEmployeesFromDepartment(Long id);

}
