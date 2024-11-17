package com.skyspace999.dao;

import java.util.List;

import com.skyspace999.dao.GenericDAO;
import com.skyspace999.domain.Meal;





public interface MealDAO extends GenericDAO<Meal, Integer> {
  
	List<Meal> findAll();
	






}


