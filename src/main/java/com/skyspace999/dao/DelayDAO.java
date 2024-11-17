package com.skyspace999.dao;

import java.util.List;

import com.skyspace999.dao.GenericDAO;
import com.skyspace999.domain.Delay;





public interface DelayDAO extends GenericDAO<Delay, Integer> {
  
	List<Delay> findAll();
	






}


