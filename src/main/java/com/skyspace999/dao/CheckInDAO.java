package com.skyspace999.dao;

import java.util.List;

import com.skyspace999.dao.GenericDAO;
import com.skyspace999.domain.CheckIn;





public interface CheckInDAO extends GenericDAO<CheckIn, Integer> {
  
	List<CheckIn> findAll();
	






}


