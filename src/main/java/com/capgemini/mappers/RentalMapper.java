package com.capgemini.mappers;

import java.util.List;
import java.util.stream.Collectors;

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
	
	public static List<RentalTO> map2TOs(List<RentalEntity> entities){
		if(entities == null){
			return null;
		}
		return entities.stream().map(RentalMapper::toRentalTO).collect(Collectors.toList());
	}
	
	public static List<RentalEntity> map2Entities(List<RentalTO> rentalTOs){
		if(rentalTOs == null){
			return null;
		}
		return rentalTOs.stream().map(RentalMapper::toRentalEntity).collect(Collectors.toList());
	}
	
	
}
