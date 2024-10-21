package com.jodiairplus3.dao;

import java.util.List;

import com.jodiairplus3.dao.GenericDAO;
import com.jodiairplus3.domain.Ticket;





public interface TicketDAO extends GenericDAO<Ticket, Integer> {
  
	List<Ticket> findAll();
	






}


