package com.skyspace999.dao;

import java.util.List;

import com.skyspace999.dao.GenericDAO;
import com.skyspace999.domain.LoungeAccess;





public interface LoungeAccessDAO extends GenericDAO<LoungeAccess, Integer> {
  
	List<LoungeAccess> findAll();
	






}


