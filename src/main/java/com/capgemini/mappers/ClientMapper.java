package com.capgemini.mappers;

import java.util.Calendar;
import java.util.List;

import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.RentalEntity;
import com.capgemini.types.ClientTO;
import com.capgemini.types.ClientTO.ClientTOBuilder;
import com.capgemini.types.RentalTO;

public class ClientMapper {

	public static ClientTO toClientTO(ClientEntity entity) {
		if (entity == null) {
			return null;
		}
		Long id = entity.getId();
		String firstName = entity.getFirstName();
		String lastName = entity.getLastName();
		String adress = entity.getAdress();
		String email = entity.getEmail();
		Calendar dateBirth = entity.getDateBirth();
		Integer phoneNumber = entity.getPhoneNumber();
		String creditCard = entity.getCreditCard();
		List<RentalTO> rentals = RentalMapper.map2TOs(entity.getRentals());
		return new ClientTOBuilder().withId(id).withFirstName(firstName).withLastName(lastName).withAdress(adress)
				.withEmail(email).withBirthDate(dateBirth).withPhoneNumber(phoneNumber).withCreditCard(creditCard)
				.withRentalsTO(rentals).build();
	}

	public static ClientEntity toClientEntity(ClientTO clientTO) {
		if (clientTO == null) {
			return null;
		}
		String firstName = clientTO.getFirstName();
		String lastName = clientTO.getLastName();
		String adress = clientTO.getAdress();
		String email = clientTO.getEmail();
		Calendar dateBirth = clientTO.getDateBirth();
		Integer phoneNumber = clientTO.getPhoneNumber();
		String creditCard = clientTO.getCreditCard();
		List<RentalEntity> rentalEntities = RentalMapper.map2Entities(clientTO.getRentals());
		return new ClientEntity(firstName, lastName, adress, email, dateBirth, phoneNumber, creditCard, rentalEntities);
	}

}
