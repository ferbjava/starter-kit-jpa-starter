package com.capgemini.mappers;

import java.sql.Timestamp;

import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.RentalEntity;
import com.capgemini.types.ClientTO;
import com.capgemini.types.ClientTO.ClientTOBuilder;
import com.capgemini.types.RentalTO;

public class ClientMapper {

	public static ClientTO toClientTO(ClientEntity entity) {
		Long id = entity.getId();
		String firstName = entity.getFirstName();
		String lastName = entity.getLastName();
		String adress = entity.getAdress();
		String email = entity.getEmail();
		Timestamp dateBirth = entity.getDateBirth();
		Integer phoneNumber = entity.getPhoneNumber();
		String creditCard = entity.getCreditCard();
		RentalTO rental = RentalMapper.toRentalTO(entity.getRental());
		return new ClientTOBuilder().withId(id).withFirstName(firstName).withLastName(lastName).withAdress(adress)
				.withEmail(email).withBirthDate(dateBirth).withPhoneNumber(phoneNumber).withCreditCard(creditCard)
				.withRentalTO(rental).build();
	}

	public static ClientEntity toClientEntity(ClientTO clientTO) {
		String firstName = clientTO.getFirstName();
		String lastName = clientTO.getLastName();
		String adress = clientTO.getAdress();
		String email = clientTO.getEmail();
		Timestamp dateBirth = clientTO.getDateBirth();
		Integer phoneNumber = clientTO.getPhoneNumber();
		String creditCard = clientTO.getCreditCard();
		RentalEntity rentalEntity = RentalMapper.toRentalEntity(clientTO.getRental());
		return new ClientEntity(firstName, lastName, adress, email, dateBirth, phoneNumber, creditCard, rentalEntity);
	}

}
