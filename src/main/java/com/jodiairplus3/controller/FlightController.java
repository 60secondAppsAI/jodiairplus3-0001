package com.jodiairplus3.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.jodiairplus3.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.jodiairplus3.domain.Flight;
import com.jodiairplus3.dto.FlightDTO;
import com.jodiairplus3.dto.FlightSearchDTO;
import com.jodiairplus3.dto.FlightPageDTO;
import com.jodiairplus3.service.FlightService;
import com.jodiairplus3.dto.common.RequestDTO;
import com.jodiairplus3.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/flight")
@RestController
public class FlightController {

	private final static Logger logger = LoggerFactory.getLogger(FlightController.class);

	@Autowired
	FlightService flightService;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Flight> getAll() {

		List<Flight> flights = flightService.findAll();
		
		return flights;	
	}

	//@ReadAccess
	@GetMapping(value = "/{flightId}")
	@ResponseBody
	public FlightDTO getFlight(@PathVariable Integer flightId) {
		
		return (flightService.getFlightDTOById(flightId));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/addFlight", method = RequestMethod.POST)
	public ResponseEntity<?> addFlight(@RequestBody FlightDTO flightDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = flightService.addFlight(flightDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/flights")
	public ResponseEntity<FlightPageDTO> getFlights(FlightSearchDTO flightSearchDTO) {
 
		return flightService.getFlights(flightSearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/updateFlight", method = RequestMethod.POST)
	public ResponseEntity<?> updateFlight(@RequestBody FlightDTO flightDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = flightService.updateFlight(flightDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
