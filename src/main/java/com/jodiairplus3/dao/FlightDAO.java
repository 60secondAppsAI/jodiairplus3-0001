package com.jodiairplus3.dao;

import java.util.List;

import com.jodiairplus3.dao.GenericDAO;
import com.jodiairplus3.domain.Flight;





public interface FlightDAO extends GenericDAO<Flight, Integer> {
  
	List<Flight> findAll();
	






}


