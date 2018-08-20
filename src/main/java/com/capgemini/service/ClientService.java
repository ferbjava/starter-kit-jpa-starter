package com.capgemini.service;

import com.capgemini.types.ClientTO;
import com.capgemini.types.RentalTO;

public interface ClientService {

	ClientTO saveClient(ClientTO client);
	ClientTO findClientById(Long id);

	RentalTO saveRental(RentalTO rental, Long clientId, Long startDepId, Long endDEpId, Long carId);

	long findClientsNo();
	long findRentalsNo();

	void deleteClient(Long id);

}
