package com.capgemini.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.types.EmployeeSearchCriteriaTO;

@Repository
public class EmployeeDaoImpl extends AbstractDao<EmployeeEntity, Long> implements EmployeeDao {

	@Override
	public List<EmployeeEntity> findEmployeesByCar(Long id) {
		TypedQuery<EmployeeEntity> query = entityManager.createQuery(
				"SELECT e FROM EmployeeEntity e INNER JOIN e.cars c WHERE c.id = :id", EmployeeEntity.class);
		return query.setParameter("id", id).getResultList();
	}

	@Override
	public List<EmployeeEntity> findEmployeesByCriteria(EmployeeSearchCriteriaTO criteria) {
		Long departmentId = criteria.getDepartmentId();
		Long positionId = criteria.getPositionId();
		Long carId = criteria.getCarId();

		if (departmentId == null && positionId == null && carId == null) {
			return new ArrayList<>();
		}

		String queryString = "SELECT e FROM EmployeeEntity e, DepartmentEntity d, PositionEntity p WHERE";
		if (departmentId != null) {
			queryString += " e MEMBER OF d.employees AND d.id = :idDep";
		}
		if (positionId != null) {
			if (departmentId != null) {
				queryString += " AND";
			}
			queryString += " e MEMBER OF p.employees AND p.id = :idPos";
		}
		if (carId != null) {
			if (departmentId != null || positionId != null) {
				queryString += " AND";
			}
			queryString += " :car MEMBER OF e.cars";
		}
		queryString += " GROUP BY e.id";

		TypedQuery<EmployeeEntity> query = entityManager.createQuery(queryString, EmployeeEntity.class);
		if (departmentId != null) {
			query.setParameter("idDep", departmentId);
		}
		if (positionId != null) {
			query.setParameter("idPos", positionId);
		}
		if (carId != null) {
			CarEntity car = entityManager.getReference(CarEntity.class, carId);
			query.setParameter("car", car);
		}

		return query.getResultList();
	}

}
