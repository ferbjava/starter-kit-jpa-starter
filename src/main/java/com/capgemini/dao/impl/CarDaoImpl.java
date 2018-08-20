package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.CarDao;
import com.capgemini.domain.CarEntity;

@Repository
public class CarDaoImpl extends AbstractDao<CarEntity, Long> implements CarDao {

	@Override
	public List<CarEntity> findCarsByType(String type) {
		TypedQuery<CarEntity> query = entityManager.createQuery("SELECT c FROM CarEntity c WHERE c.type = :type",
				CarEntity.class);
		return query.setParameter("type", type).getResultList();
	}

	@Override
	public List<CarEntity> findCarsByBrand(String brand) {
		TypedQuery<CarEntity> query = entityManager.createQuery("SELECT c FROM CarEntity c WHERE c.brand = :brand",
				CarEntity.class);
		return query.setParameter("brand", brand).getResultList();
	}

	@Override
	public List<CarEntity> findCarsWithOver10Clients() {
		TypedQuery<CarEntity> query = entityManager.createQuery(
				"SELECT c FROM CarEntity c, ClientsEntity cl WHERE SELECT( COUNT(cl) FROM ClientsEntity cl HAVING COUNT(DISTINCT(cl))>10)"
				, CarEntity.class);
		return query.getResultList();
	}

}
