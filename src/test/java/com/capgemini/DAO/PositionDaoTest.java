package com.capgemini.DAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.Utils.InsertData;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.PositionDao;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.PositionEntity;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.mappers.PositionMapper;

@RunWith(SpringRunner.class)
@SpringBootTest

public class PositionDaoTest {

	@Autowired
	private PositionDao positionDao;

	@Autowired
	private EmployeeDao employeeDao;

	@Test
	@Transactional
	public void shouldFindPositionbyEmployeeId() {
		// given
		InsertData data = new InsertData();
		PositionEntity positionEntity = PositionMapper.toPositionEntity(data.getPosById(1));
		EmployeeEntity employeeEntity = EmployeeMapper.toEmployeeEntity(data.getEmplById(0));
		positionEntity.addEmployee(employeeEntity);
		PositionEntity savedPosition = positionDao.save(positionEntity);
		EmployeeEntity savedEmployeeVer01 = employeeDao.getOne(1L);

		// when
		EmployeeEntity savedEmployeeVer02 = savedPosition.getEmployees().get(0);
		PositionEntity selectedPosition = positionDao.findPositionByEmployeeId(savedEmployeeVer02.getId());

		// then
		assertTrue(savedEmployeeVer01.getId() == savedEmployeeVer02.getId());
		assertEquals(savedPosition.getId(), selectedPosition.getId());
	}

}
