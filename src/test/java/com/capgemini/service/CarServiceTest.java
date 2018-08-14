package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.types.CarTO;

@RunWith(SpringRunner.class)
@SpringBootTest

public class CarServiceTest {

	 @Autowired
	 private CarService carService;
	 
	 @Test
	 public void shouldFindCarById(){
		 // given
		 CarTO car = new CarTO("combii", "VW", "Golf", 2010, "black", 1998, 250, 170000);
		 CarTO savedCar = carService.saveCar(car);
		 
		 // when
		 CarTO selectedCar = carService.findCarById(savedCar.getId());
		 
		 //then
		assertNotNull(selectedCar);
		assertEquals(savedCar.getBrand(), selectedCar.getBrand());
		assertEquals(savedCar.getId(), selectedCar.getId());
		 
	 }
	 
}
