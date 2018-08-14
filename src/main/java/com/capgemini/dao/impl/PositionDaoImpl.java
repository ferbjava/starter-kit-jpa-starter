package com.capgemini.dao.impl;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.PositionDao;
import com.capgemini.domain.PositionEntity;

@Repository
public class PositionDaoImpl extends AbstractDao<PositionEntity, Long> implements PositionDao {

}
