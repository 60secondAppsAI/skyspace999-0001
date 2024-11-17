package com.skyspace999.dao;

import java.util.List;

import com.skyspace999.dao.GenericDAO;
import com.skyspace999.domain.Flight;





public interface FlightDAO extends GenericDAO<Flight, Integer> {
  
	List<Flight> findAll();
	






}


