package com.capgemini.dao;

import java.util.List;

import com.capgemini.Utils.EmployeeSearchCriteria;
import com.capgemini.domain.EmployeeEntity;

public interface EmployeeDao extends Dao<EmployeeEntity, Long> {

	List<EmployeeEntity> findEmployeesByCar(Long id);
	List<EmployeeEntity> findEmployeeByCriteria(EmployeeSearchCriteria criteria);

}
