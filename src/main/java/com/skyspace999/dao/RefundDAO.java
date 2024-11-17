package com.skyspace999.dao;

import java.util.List;

import com.skyspace999.dao.GenericDAO;
import com.skyspace999.domain.Refund;





public interface RefundDAO extends GenericDAO<Refund, Integer> {
  
	List<Refund> findAll();
	






}


