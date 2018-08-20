package com.capgemini.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.ClientDao;
import com.capgemini.dao.DepartmentDao;
import com.capgemini.dao.RentalDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.DepartmentEntity;
import com.capgemini.domain.RentalEntity;
import com.capgemini.mappers.ClientMapper;
import com.capgemini.mappers.RentalMapper;
import com.capgemini.service.ClientService;
import com.capgemini.types.ClientTO;
import com.capgemini.types.RentalTO;

@Service
@Transactional(readOnly = true)
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientDao clientRepository;
	
	@Autowired
	private RentalDao rentalRepository;
	
	@Autowired
	private DepartmentDao departmentRepository;
	
	@Autowired
	private CarDao carRepository;

	@Override
	@Transactional(readOnly = false)
	public ClientTO saveClient(ClientTO client) {
		ClientEntity clientEntity = clientRepository.save(ClientMapper.toClientEntity(client));
		return ClientMapper.toClientTO(clientEntity);
	}
	
	@Override
	@Transactional(readOnly = false)
	public RentalTO saveRental(RentalTO rental, Long clientId, Long startDepId, Long endDepId, Long carId) {
		RentalEntity rentalEntity = rentalRepository.save(RentalMapper.toRentalEntity(rental));
		if(clientId!= null){
			ClientEntity clientEntity = clientRepository.getOne(clientId);
			clientEntity.addRental(rentalEntity);
		}
		if(startDepId!= null){
			DepartmentEntity startDepEntity = departmentRepository.getOne(startDepId);
			startDepEntity.addRentalStart(rentalEntity);
		}
		if(endDepId!= null){
			DepartmentEntity endDepEntity = departmentRepository.getOne(endDepId);
			endDepEntity.addRentalStart(rentalEntity);
		}
		if(carId!= null){
			CarEntity carEntity = carRepository.getOne(carId);
			carEntity.addRental(rentalEntity);
		}
		return RentalMapper.toRentalTO(rentalEntity);
	}

	@Override
	public ClientTO findClientById(Long id) {
		ClientEntity entity = clientRepository.findOne(id);
		return ClientMapper.toClientTO(entity);
	}

	@Override
	public long findClientsNo() {
		return clientRepository.count();
	}

	@Override
	public long findRentalsNo() {
		return rentalRepository.count();
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteClient(Long id) {
		clientRepository.delete(id);
	}

}
