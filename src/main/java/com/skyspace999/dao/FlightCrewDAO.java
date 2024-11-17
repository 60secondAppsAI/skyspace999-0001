package com.skyspace999.dao;

import java.util.List;

import com.skyspace999.dao.GenericDAO;
import com.skyspace999.domain.FlightCrew;





public interface FlightCrewDAO extends GenericDAO<FlightCrew, Integer> {
  
	List<FlightCrew> findAll();
	






}


