package com.skyspace999.dao;

import java.util.List;

import com.skyspace999.dao.GenericDAO;
import com.skyspace999.domain.CrewMember;





public interface CrewMemberDAO extends GenericDAO<CrewMember, Integer> {
  
	List<CrewMember> findAll();
	






}


