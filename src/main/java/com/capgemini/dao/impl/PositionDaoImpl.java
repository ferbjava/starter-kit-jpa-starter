package com.capgemini.dao.impl;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.PositionDao;
import com.capgemini.domain.PositionEntity;

@Repository
public class PositionDaoImpl extends AbstractDao<PositionEntity, Long> implements PositionDao {

	@Override
	public PositionEntity findPositionByEmployeeId(Long id) {
		TypedQuery<PositionEntity> query = entityManager.createQuery(
				"SELECT p FROM PositionEntity p INNER JOIN p.employees e WHERE e.id = :id", PositionEntity.class);
		return query.setParameter("id", id).getSingleResult();
	}

}
