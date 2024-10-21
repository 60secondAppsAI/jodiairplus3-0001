package com.jodiairplus3.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.jodiairplus3.dao.GenericDAO;
import com.jodiairplus3.service.GenericService;
import com.jodiairplus3.service.impl.GenericServiceImpl;
import com.jodiairplus3.dao.FlightCrewDAO;
import com.jodiairplus3.domain.FlightCrew;
import com.jodiairplus3.dto.FlightCrewDTO;
import com.jodiairplus3.dto.FlightCrewSearchDTO;
import com.jodiairplus3.dto.FlightCrewPageDTO;
import com.jodiairplus3.dto.FlightCrewConvertCriteriaDTO;
import com.jodiairplus3.dto.common.RequestDTO;
import com.jodiairplus3.dto.common.ResultDTO;
import com.jodiairplus3.service.FlightCrewService;
import com.jodiairplus3.util.ControllerUtils;





@Service
public class FlightCrewServiceImpl extends GenericServiceImpl<FlightCrew, Integer> implements FlightCrewService {

    private final static Logger logger = LoggerFactory.getLogger(FlightCrewServiceImpl.class);

	@Autowired
	FlightCrewDAO flightCrewDao;

	


	@Override
	public GenericDAO<FlightCrew, Integer> getDAO() {
		return (GenericDAO<FlightCrew, Integer>) flightCrewDao;
	}
	
	public List<FlightCrew> findAll () {
		List<FlightCrew> flightCrews = flightCrewDao.findAll();
		
		return flightCrews;	
		
	}

	public ResultDTO addFlightCrew(FlightCrewDTO flightCrewDTO, RequestDTO requestDTO) {

		FlightCrew flightCrew = new FlightCrew();

		flightCrew.setFlightCrewId(flightCrewDTO.getFlightCrewId());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		flightCrew = flightCrewDao.save(flightCrew);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<FlightCrew> getAllFlightCrews(Pageable pageable) {
		return flightCrewDao.findAll(pageable);
	}

	public Page<FlightCrew> getAllFlightCrews(Specification<FlightCrew> spec, Pageable pageable) {
		return flightCrewDao.findAll(spec, pageable);
	}

	public ResponseEntity<FlightCrewPageDTO> getFlightCrews(FlightCrewSearchDTO flightCrewSearchDTO) {
	
			Integer flightCrewId = flightCrewSearchDTO.getFlightCrewId(); 
 			String sortBy = flightCrewSearchDTO.getSortBy();
			String sortOrder = flightCrewSearchDTO.getSortOrder();
			String searchQuery = flightCrewSearchDTO.getSearchQuery();
			Integer page = flightCrewSearchDTO.getPage();
			Integer size = flightCrewSearchDTO.getSize();

	        Specification<FlightCrew> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, flightCrewId, "flightCrewId"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<FlightCrew> flightCrews = this.getAllFlightCrews(spec, pageable);
		
		//System.out.println(String.valueOf(flightCrews.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(flightCrews.getTotalPages()));
		
		List<FlightCrew> flightCrewsList = flightCrews.getContent();
		
		FlightCrewConvertCriteriaDTO convertCriteria = new FlightCrewConvertCriteriaDTO();
		List<FlightCrewDTO> flightCrewDTOs = this.convertFlightCrewsToFlightCrewDTOs(flightCrewsList,convertCriteria);
		
		FlightCrewPageDTO flightCrewPageDTO = new FlightCrewPageDTO();
		flightCrewPageDTO.setFlightCrews(flightCrewDTOs);
		flightCrewPageDTO.setTotalElements(flightCrews.getTotalElements());
		return ResponseEntity.ok(flightCrewPageDTO);
	}

	public List<FlightCrewDTO> convertFlightCrewsToFlightCrewDTOs(List<FlightCrew> flightCrews, FlightCrewConvertCriteriaDTO convertCriteria) {
		
		List<FlightCrewDTO> flightCrewDTOs = new ArrayList<FlightCrewDTO>();
		
		for (FlightCrew flightCrew : flightCrews) {
			flightCrewDTOs.add(convertFlightCrewToFlightCrewDTO(flightCrew,convertCriteria));
		}
		
		return flightCrewDTOs;

	}
	
	public FlightCrewDTO convertFlightCrewToFlightCrewDTO(FlightCrew flightCrew, FlightCrewConvertCriteriaDTO convertCriteria) {
		
		FlightCrewDTO flightCrewDTO = new FlightCrewDTO();
		
		flightCrewDTO.setFlightCrewId(flightCrew.getFlightCrewId());

	

		
		return flightCrewDTO;
	}

	public ResultDTO updateFlightCrew(FlightCrewDTO flightCrewDTO, RequestDTO requestDTO) {
		
		FlightCrew flightCrew = flightCrewDao.getById(flightCrewDTO.getFlightCrewId());

		flightCrew.setFlightCrewId(ControllerUtils.setValue(flightCrew.getFlightCrewId(), flightCrewDTO.getFlightCrewId()));



        flightCrew = flightCrewDao.save(flightCrew);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public FlightCrewDTO getFlightCrewDTOById(Integer flightCrewId) {
	
		FlightCrew flightCrew = flightCrewDao.getById(flightCrewId);
			
		
		FlightCrewConvertCriteriaDTO convertCriteria = new FlightCrewConvertCriteriaDTO();
		return(this.convertFlightCrewToFlightCrewDTO(flightCrew,convertCriteria));
	}







}
