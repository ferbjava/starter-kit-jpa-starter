package com.capgemini.mappers;

import com.capgemini.domain.PositionEntity;
import com.capgemini.types.PositionTO;
import com.capgemini.types.PositionTO.PositionTOBuilder;

public class PositionMapper {

	public static PositionTO toPositionTO(PositionEntity entity) {
		if (entity == null) {
			return null;
		}
		return new PositionTOBuilder().withId(entity.getId()).withPosition(entity.getPosition())
				.withEmployees(EmployeeMapper.map2TOs(entity.getEmployees())).build();
	}

	public static PositionEntity toPositionEntity(PositionTO positionTO) {
		if (positionTO == null) {
			return null;
		}
		return new PositionEntity(positionTO.getPosition(), EmployeeMapper.map2Entities(positionTO.getEmployees()));
	}
}
