package com.capgemini.DAO;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.Utils.InsertData;
import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.mappers.CarMapper;
import com.capgemini.mappers.EmployeeMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
public class EmployeeDaoTest {

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private CarDao carDao;

	@Test
	@Transactional
	public void shouldFindEmployeesByCarId() {
		// given
		InsertData data = new InsertData();
		CarEntity car01 = CarMapper.toCarEntity(data.getCarById(0));
		CarEntity car02 = CarMapper.toCarEntity(data.getCarById(1));
		EmployeeEntity employee01 = EmployeeMapper.toEmployeeEntity(data.getEmplById(0));
		EmployeeEntity employee02 = EmployeeMapper.toEmployeeEntity(data.getEmplById(1));
		employee01.addCar(car01);
		employee01.addCar(car02);
		employee02.addCar(car02);
		EmployeeEntity savedEmp01 = employeeDao.save(employee01);
		EmployeeEntity savedEmp02 = employeeDao.save(employee02);

		// when
		List<CarEntity> savedCars = carDao.findAll();
		long carsNo = carDao.count();
		List<EmployeeEntity> firstCarCarers = employeeDao.findEmployeesByCar(1L);
		List<EmployeeEntity> secondCarCarers = employeeDao.findEmployeesByCar(2L);

		// then
		assertEquals((long) savedCars.size(), carsNo);
		assertEquals(firstCarCarers.get(0).getId(), savedEmp01.getId());
		assertEquals(secondCarCarers.get(0).getId(), savedEmp01.getId());
		assertEquals(secondCarCarers.get(1).getId(), savedEmp02.getId());
	}

}
