package com.skyspace999.dao;

import java.util.List;

import com.skyspace999.dao.GenericDAO;
import com.skyspace999.domain.ConnectionFlight;





public interface ConnectionFlightDAO extends GenericDAO<ConnectionFlight, Integer> {
  
	List<ConnectionFlight> findAll();
	






}


