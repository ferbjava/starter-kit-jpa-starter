package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.Utils.EmployeeSearchCriteria;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.EmployeeEntity;

@Repository
public class EmployeeDaoImpl extends AbstractDao<EmployeeEntity, Long> implements EmployeeDao {

	@Override
	public List<EmployeeEntity> findEmployeesByCar(Long id) {
		TypedQuery<EmployeeEntity> query = entityManager.createQuery(
				"SELECT e FROM EmployeeEntity e INNER JOIN e.cars c WHERE c.id = :id", EmployeeEntity.class);
		return query.setParameter("id", id).getResultList();
	}

	@Override
	public List<EmployeeEntity> findEmployeeByCriteria(EmployeeSearchCriteria criteria) {
		Long depertmentId = criteria.getDepartmentId();
//		Long positionId = criteria.getPositionId();
//		Long carId = criteria.getCarId();
		TypedQuery<EmployeeEntity> query = entityManager.createQuery(
				"SELECT e FROM DepartmentEntity d INNER JOIN d.employees e WHERE d.id = :id", EmployeeEntity.class);
		return query.setParameter("id", depertmentId).getResultList();
	}

}
