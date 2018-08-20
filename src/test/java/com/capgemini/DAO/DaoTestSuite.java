package com.capgemini.DAO;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DepartmentDaoTest.class, EmployeeDaoTest.class, PositionDaoTest.class  })
public class DaoTestSuite {

}
