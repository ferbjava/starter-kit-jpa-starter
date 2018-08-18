package com.capgemini.mappers;

import com.capgemini.domain.RentalEntity;
import com.capgemini.types.RentalTO;
import com.capgemini.types.RentalTO.RentalTOBuilder;

public class RentalMapper {

	public static RentalTO toRentalTO(RentalEntity entity) {
		if (entity == null) {
			return null;
		}
		return new RentalTOBuilder().withId(entity.getId()).withDateStart(entity.getDateStart())
				.withDateStop(entity.getDateStop()).withCharge(entity.getCharge()).build();
	}

	public static RentalEntity toRentalEntity(RentalTO rentalTO) {
		if (rentalTO == null) {
			return null;
		}
		return new RentalEntity(rentalTO.getDateStart(), rentalTO.getDateStop(), rentalTO.getCharge());
	}
}
