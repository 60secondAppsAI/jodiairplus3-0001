package com.jodiairplus3.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.jodiairplus3.domain.Airport;
import com.jodiairplus3.dto.AirportDTO;
import com.jodiairplus3.dto.AirportSearchDTO;
import com.jodiairplus3.dto.AirportPageDTO;
import com.jodiairplus3.dto.AirportConvertCriteriaDTO;
import com.jodiairplus3.service.GenericService;
import com.jodiairplus3.dto.common.RequestDTO;
import com.jodiairplus3.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AirportService extends GenericService<Airport, Integer> {

	List<Airport> findAll();

	ResultDTO addAirport(AirportDTO airportDTO, RequestDTO requestDTO);

	ResultDTO updateAirport(AirportDTO airportDTO, RequestDTO requestDTO);

    Page<Airport> getAllAirports(Pageable pageable);

    Page<Airport> getAllAirports(Specification<Airport> spec, Pageable pageable);

	ResponseEntity<AirportPageDTO> getAirports(AirportSearchDTO airportSearchDTO);
	
	List<AirportDTO> convertAirportsToAirportDTOs(List<Airport> airports, AirportConvertCriteriaDTO convertCriteria);

	AirportDTO getAirportDTOById(Integer airportId);







}





