package com.capgemini.DAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.DepartmentDao;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.DepartmentEntity;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.utils.InsertData;
import com.capgemini.mappers.DepartmentMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
public class DepartmentDaoTest {

	@Autowired
	private DepartmentDao departmentDao;

	@Autowired
	private EmployeeDao employeeDao;

	@Test
	@Transactional
	public void shouldFindDepartmetnByEmployeeId() {
		// given
		InsertData data = new InsertData();
		data.initialize();
		
		DepartmentEntity departmentEntity = DepartmentMapper.toDepartmentEntity(data.getDepById(1));
		EmployeeEntity employeeEntity = EmployeeMapper.toEmployeeEntity(data.getEmplById(0));
		departmentEntity.addEmployee(employeeEntity);
		DepartmentEntity savedDepartment = departmentDao.save(departmentEntity);
		EmployeeEntity savedEmployeeVer01 = employeeDao.getOne(1L);

		// when
		EmployeeEntity savedEmployeeVer02 = savedDepartment.getEmployees().get(0);
		DepartmentEntity selectedDepartment = departmentDao.findDepartmentByEmployeeId(savedEmployeeVer02.getId());

		// then
		assertTrue(savedEmployeeVer01.getId() == savedEmployeeVer02.getId());
		assertEquals(savedDepartment.getId(), selectedDepartment.getId());
	}

}
