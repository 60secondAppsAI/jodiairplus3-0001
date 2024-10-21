package com.jodiairplus3.dao;

import java.util.List;

import com.jodiairplus3.dao.GenericDAO;
import com.jodiairplus3.domain.User;

import java.util.Optional;




public interface UserDAO extends GenericDAO<User, Integer> {
  
	List<User> findAll();
	






}


