package com.capgemini.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.CarDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.mappers.CarMapper;
import com.capgemini.service.CarService;
import com.capgemini.types.CarTO;

@Service
@Transactional(readOnly = true)
public class CarServiceImpl implements CarService {

	@Autowired
	private CarDao carRepository;
	
	

	@Override
	@Transactional(readOnly = false)
	public CarTO saveCar(CarTO car) {
		CarEntity carEntity =carRepository.save(CarMapper.toCarEntity(car));
		return CarMapper.toCarTO(carEntity);
	}

	@Override
	public CarTO findCarById(Long id) {
		CarEntity carEntity = carRepository.findOne(id);
		return CarMapper.toCarTO(carEntity);
	}
}
