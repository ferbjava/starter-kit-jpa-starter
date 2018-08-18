package com.capgemini.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.DepartmentDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.PositionDao;
import com.capgemini.domain.DepartmentEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.PositionEntity;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.mappers.PositionMapper;
import com.capgemini.service.DepartmentService;
import com.capgemini.types.DepartmentTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.PositionTO;

@Service
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentDao departmentRepository;
	
	@Autowired
	private PositionDao positionRepository;

	@Autowired
	private EmployeeDao employeeRepository;


	@Override
	@Transactional(readOnly = false)
	public DepartmentTO saveDepartment(DepartmentTO department) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DepartmentTO findDepartmentById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@Transactional(readOnly = false)
	public PositionTO savePosition(PositionTO position) {
		PositionEntity entity = positionRepository.save(PositionMapper.toPositionEntity(position));
		return PositionMapper.toPositionTO(entity);
	}

	@Override
	public PositionTO findPositionById(Long id) {
		PositionEntity entity = positionRepository.findOne(id);
		return PositionMapper.toPositionTO(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public EmployeeTO saveEmployee(EmployeeTO employee, Long idDep, Long idPos) {
		EmployeeEntity entity = employeeRepository.save(EmployeeMapper.toEmployeeEntity(employee));
		if(idDep!=null){
			DepartmentEntity depEntity = departmentRepository.getOne(idDep);
			depEntity.addEmployee(entity);
		}
		if(idPos!=null){
			PositionEntity positionEntity = positionRepository.getOne(idPos);
			positionEntity.addEmployee(entity);
		}
		return EmployeeMapper.toEmployeeTO(entity);
	}

	@Override
	public EmployeeTO findEmployeeById(Long id) {
		EmployeeEntity entity = employeeRepository.findOne(id);
		return EmployeeMapper.toEmployeeTO(entity);
	}

	@Override
	public long findDepartmentNo() {
		return departmentRepository.count();
	}

	@Override
	public long findPositionsNo() {
		return positionRepository.count();
	}

	@Override
	public long findEmployeesNo() {
		return employeeRepository.count();
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteDepartment(Long id) {
		// TODO Auto-generated method stub
	}

	@Override
	@Transactional(readOnly = false)
	public void deletePosition(Long id) {
		positionRepository.delete(id);
		
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEmployee(Long id) {
		employeeRepository.delete(id);
	}

}
