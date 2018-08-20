package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.DepartmentDao;
import com.capgemini.domain.DepartmentEntity;
import com.capgemini.domain.EmployeeEntity;

@Repository
public class DepartmentDaoImpl extends AbstractDao<DepartmentEntity, Long> implements DepartmentDao {

	@Override
	public DepartmentEntity findDepartmentByEmployeeId(Long id) {
		TypedQuery<DepartmentEntity> query = entityManager.createQuery(
				"SELECT d FROM DepartmentEntity d INNER JOIN d.employees e WHERE e.id = :id",
				DepartmentEntity.class);
		return query.setParameter("id", id).getSingleResult();
	}

	@Override
	public List<EmployeeEntity> findEmployeesFromDepartment(Long id) {
		return getOne(id).getEmployees();
	}
	
}
