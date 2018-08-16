package com.capgemini.mappers;

import com.capgemini.domain.PositionEntity;
import com.capgemini.types.PositionTO;
import com.capgemini.types.PositionTO.PositionTOBuilder;

public class PositionMapper {

	public static PositionTO toPositionTO(PositionEntity entity) {
		return new PositionTOBuilder().withId(entity.getId()).withPosition(entity.getPosition()).build();
	}

	public static PositionEntity toPositionEntity(PositionTO positionTo) {
		return new PositionEntity(positionTo.getPosition());
	}
}
