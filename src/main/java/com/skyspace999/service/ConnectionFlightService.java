package com.skyspace999.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace999.domain.ConnectionFlight;
import com.skyspace999.dto.ConnectionFlightDTO;
import com.skyspace999.dto.ConnectionFlightSearchDTO;
import com.skyspace999.dto.ConnectionFlightPageDTO;
import com.skyspace999.dto.ConnectionFlightConvertCriteriaDTO;
import com.skyspace999.service.GenericService;
import com.skyspace999.dto.common.RequestDTO;
import com.skyspace999.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ConnectionFlightService extends GenericService<ConnectionFlight, Integer> {

	List<ConnectionFlight> findAll();

	ResultDTO addConnectionFlight(ConnectionFlightDTO connectionFlightDTO, RequestDTO requestDTO);

	ResultDTO updateConnectionFlight(ConnectionFlightDTO connectionFlightDTO, RequestDTO requestDTO);

    Page<ConnectionFlight> getAllConnectionFlights(Pageable pageable);

    Page<ConnectionFlight> getAllConnectionFlights(Specification<ConnectionFlight> spec, Pageable pageable);

	ResponseEntity<ConnectionFlightPageDTO> getConnectionFlights(ConnectionFlightSearchDTO connectionFlightSearchDTO);
	
	List<ConnectionFlightDTO> convertConnectionFlightsToConnectionFlightDTOs(List<ConnectionFlight> connectionFlights, ConnectionFlightConvertCriteriaDTO convertCriteria);

	ConnectionFlightDTO getConnectionFlightDTOById(Integer connectionFlightId);







}





