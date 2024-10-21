package com.jodiairplus3.dao;

import java.util.List;

import com.jodiairplus3.dao.GenericDAO;
import com.jodiairplus3.domain.Pilot;





public interface PilotDAO extends GenericDAO<Pilot, Integer> {
  
	List<Pilot> findAll();
	






}


