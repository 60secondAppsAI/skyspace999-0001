package com.skyspace999.dao;

import java.util.List;

import com.skyspace999.dao.GenericDAO;
import com.skyspace999.domain.Baggage;





public interface BaggageDAO extends GenericDAO<Baggage, Integer> {
  
	List<Baggage> findAll();
	






}


