package com.skyspace999.dao;

import java.util.List;

import com.skyspace999.dao.GenericDAO;
import com.skyspace999.domain.FrequentFlyer;





public interface FrequentFlyerDAO extends GenericDAO<FrequentFlyer, Integer> {
  
	List<FrequentFlyer> findAll();
	






}


