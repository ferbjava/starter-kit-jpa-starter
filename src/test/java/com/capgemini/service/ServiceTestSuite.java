package com.capgemini.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CarServiceTest.class, DepartmentServiceTest.class, ClientServiceTest.class })
public class ServiceTestSuite {

}
