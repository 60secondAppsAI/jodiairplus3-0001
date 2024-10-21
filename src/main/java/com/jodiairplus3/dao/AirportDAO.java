package com.jodiairplus3.dao;

import java.util.List;

import com.jodiairplus3.dao.GenericDAO;
import com.jodiairplus3.domain.Airport;





public interface AirportDAO extends GenericDAO<Airport, Integer> {
  
	List<Airport> findAll();
	






}


