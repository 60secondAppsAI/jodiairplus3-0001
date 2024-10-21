package com.jodiairplus3.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FlightCrewPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<FlightCrewDTO> flightCrews;
}





