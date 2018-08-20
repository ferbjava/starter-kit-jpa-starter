package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.mappers.CarMapper;
import com.capgemini.service.CarService;
import com.capgemini.types.CarTO;

@Service
@Transactional(readOnly = true)
public class CarServiceImpl implements CarService {

	@Autowired
	private CarDao carRepository;

	@Autowired
	private EmployeeDao employeeRepository;

	@Override
	@Transactional(readOnly = false)
	public CarTO saveCar(CarTO car) {
		CarEntity carEntity = carRepository.save(CarMapper.toCarEntity(car));
		return CarMapper.toCarTO(carEntity);
	}

	@Override
	public long findCarNo() {
		return carRepository.count();
	}

	@Override
	public CarTO findCarById(Long id) {
		CarEntity carEntity = carRepository.findOne(id);
		return CarMapper.toCarTO(carEntity);
	}

	@Override
	public CarTO updateCar(CarTO car) {
		CarEntity carEntity = carRepository.update(CarMapper.toCarEntity(car));
		return CarMapper.toCarTO(carEntity);
	}

	@Override
	public List<CarTO> findCarsByEmployee(Long id) {
		return CarMapper.map2TOs(employeeRepository.getOne(id).getCars());
	}

	@Override
	public void addCarer(Long carId, Long emloyeeId) {
		EmployeeEntity employeeEntity = employeeRepository.getOne(emloyeeId);
		employeeEntity.addCar(carRepository.getOne(carId));
	}

	@Override
	public void deleteCar(Long id) {
		if (employeeRepository.count() != 0) {
			List<EmployeeEntity> entities = employeeRepository.findEmployeesByCar(id);
			for (EmployeeEntity e : entities) {
				e.removeCarById(id);
			}
		}
		carRepository.delete(id);
	}

	@Override
	public List<CarTO> findCarsByType(String type) {
		return CarMapper.map2TOs(carRepository.findCarsByType(type));
	}

	@Override
	public List<CarTO> findCarsByBrand(String brand) {
		return CarMapper.map2TOs(carRepository.findCarsByBrand(brand));
	}

}
