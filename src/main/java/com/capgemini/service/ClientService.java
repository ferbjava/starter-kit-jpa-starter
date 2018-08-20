package com.capgemini.service;

import com.capgemini.types.ClientTO;
import com.capgemini.types.RentalTO;

public interface ClientService {

	ClientTO saveClient(ClientTO client);

	RentalTO saveRental(RentalTO rental);

	long findClientsNo();
	long findRentalsNo();

	void deleteClient(Long id);

}
