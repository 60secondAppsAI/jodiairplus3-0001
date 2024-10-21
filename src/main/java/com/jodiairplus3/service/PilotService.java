package com.jodiairplus3.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.jodiairplus3.domain.Pilot;
import com.jodiairplus3.dto.PilotDTO;
import com.jodiairplus3.dto.PilotSearchDTO;
import com.jodiairplus3.dto.PilotPageDTO;
import com.jodiairplus3.dto.PilotConvertCriteriaDTO;
import com.jodiairplus3.service.GenericService;
import com.jodiairplus3.dto.common.RequestDTO;
import com.jodiairplus3.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PilotService extends GenericService<Pilot, Integer> {

	List<Pilot> findAll();

	ResultDTO addPilot(PilotDTO pilotDTO, RequestDTO requestDTO);

	ResultDTO updatePilot(PilotDTO pilotDTO, RequestDTO requestDTO);

    Page<Pilot> getAllPilots(Pageable pageable);

    Page<Pilot> getAllPilots(Specification<Pilot> spec, Pageable pageable);

	ResponseEntity<PilotPageDTO> getPilots(PilotSearchDTO pilotSearchDTO);
	
	List<PilotDTO> convertPilotsToPilotDTOs(List<Pilot> pilots, PilotConvertCriteriaDTO convertCriteria);

	PilotDTO getPilotDTOById(Integer pilotId);







}





