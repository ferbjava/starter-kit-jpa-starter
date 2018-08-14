package com.capgemini.mappers;

import com.capgemini.domain.PositionEntity;
import com.capgemini.types.PositionTO;

public class PositionMapper {
	
	public static PositionTO toPositionTO(PositionEntity entity){
		return new PositionTO(entity.getId(), entity.getPosition());
	}
	
	public static PositionEntity toPositionEntity(PositionTO positionTo){
		return new PositionEntity(positionTo.getPosition());
	}
}
