package com.skyspace999.dao;

import java.util.List;

import com.skyspace999.dao.GenericDAO;
import com.skyspace999.domain.SeatAssignment;





public interface SeatAssignmentDAO extends GenericDAO<SeatAssignment, Integer> {
  
	List<SeatAssignment> findAll();
	






}


