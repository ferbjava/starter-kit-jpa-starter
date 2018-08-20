package com.capgemini.service;

import java.util.List;

import com.capgemini.types.CarTO;

public interface CarService {

	CarTO saveCar(CarTO car);
	CarTO updateCar(CarTO car);
	CarTO findCarById(Long id);
	List<CarTO> findCarsByEmployee(Long id);
	List<CarTO> findCarsByType(String type);
	List<CarTO> findCarsByBrand(String brand);
	
	long findCarNo();
	
	void addCarer(Long carId, Long emloyeeId);
	void deleteCar(Long id);

}
