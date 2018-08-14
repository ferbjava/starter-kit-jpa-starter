package com.capgemini.dao.impl;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.CarDao;
import com.capgemini.domain.CarEntity;

@Repository
public class CarDaoImpl extends AbstractDao<CarEntity, Long> implements CarDao {

}
