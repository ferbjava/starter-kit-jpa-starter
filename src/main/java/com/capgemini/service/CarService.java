package com.capgemini.service;

import com.capgemini.types.CarTO;

public interface CarService {

	CarTO saveCar(CarTO car);

	CarTO findCarById(Long id);

}
