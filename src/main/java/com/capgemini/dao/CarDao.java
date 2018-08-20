package com.capgemini.dao;

import java.util.List;

import com.capgemini.domain.CarEntity;

public interface CarDao extends Dao<CarEntity, Long> {
	
	List<CarEntity> findCarsByType(String type);
	List<CarEntity> findCarsByBrand(String brand);

}
