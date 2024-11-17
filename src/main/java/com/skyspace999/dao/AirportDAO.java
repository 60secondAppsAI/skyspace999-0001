package com.skyspace999.dao;

import java.util.List;

import com.skyspace999.dao.GenericDAO;
import com.skyspace999.domain.Airport;





public interface AirportDAO extends GenericDAO<Airport, Integer> {
  
	List<Airport> findAll();
	






}


