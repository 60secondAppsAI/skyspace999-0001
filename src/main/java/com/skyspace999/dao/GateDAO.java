package com.skyspace999.dao;

import java.util.List;

import com.skyspace999.dao.GenericDAO;
import com.skyspace999.domain.Gate;





public interface GateDAO extends GenericDAO<Gate, Integer> {
  
	List<Gate> findAll();
	






}


