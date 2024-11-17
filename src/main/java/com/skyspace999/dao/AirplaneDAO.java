package com.skyspace999.dao;

import java.util.List;

import com.skyspace999.dao.GenericDAO;
import com.skyspace999.domain.Airplane;





public interface AirplaneDAO extends GenericDAO<Airplane, Integer> {
  
	List<Airplane> findAll();
	






}


