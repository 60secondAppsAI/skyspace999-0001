package com.skyspace999.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace999.domain.FlightCrew;
import com.skyspace999.dto.FlightCrewDTO;
import com.skyspace999.dto.FlightCrewSearchDTO;
import com.skyspace999.dto.FlightCrewPageDTO;
import com.skyspace999.dto.FlightCrewConvertCriteriaDTO;
import com.skyspace999.service.GenericService;
import com.skyspace999.dto.common.RequestDTO;
import com.skyspace999.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface FlightCrewService extends GenericService<FlightCrew, Integer> {

	List<FlightCrew> findAll();

	ResultDTO addFlightCrew(FlightCrewDTO flightCrewDTO, RequestDTO requestDTO);

	ResultDTO updateFlightCrew(FlightCrewDTO flightCrewDTO, RequestDTO requestDTO);

    Page<FlightCrew> getAllFlightCrews(Pageable pageable);

    Page<FlightCrew> getAllFlightCrews(Specification<FlightCrew> spec, Pageable pageable);

	ResponseEntity<FlightCrewPageDTO> getFlightCrews(FlightCrewSearchDTO flightCrewSearchDTO);
	
	List<FlightCrewDTO> convertFlightCrewsToFlightCrewDTOs(List<FlightCrew> flightCrews, FlightCrewConvertCriteriaDTO convertCriteria);

	FlightCrewDTO getFlightCrewDTOById(Integer flightCrewId);







}





