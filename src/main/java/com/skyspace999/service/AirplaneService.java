package com.skyspace999.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace999.domain.Airplane;
import com.skyspace999.dto.AirplaneDTO;
import com.skyspace999.dto.AirplaneSearchDTO;
import com.skyspace999.dto.AirplanePageDTO;
import com.skyspace999.dto.AirplaneConvertCriteriaDTO;
import com.skyspace999.service.GenericService;
import com.skyspace999.dto.common.RequestDTO;
import com.skyspace999.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AirplaneService extends GenericService<Airplane, Integer> {

	List<Airplane> findAll();

	ResultDTO addAirplane(AirplaneDTO airplaneDTO, RequestDTO requestDTO);

	ResultDTO updateAirplane(AirplaneDTO airplaneDTO, RequestDTO requestDTO);

    Page<Airplane> getAllAirplanes(Pageable pageable);

    Page<Airplane> getAllAirplanes(Specification<Airplane> spec, Pageable pageable);

	ResponseEntity<AirplanePageDTO> getAirplanes(AirplaneSearchDTO airplaneSearchDTO);
	
	List<AirplaneDTO> convertAirplanesToAirplaneDTOs(List<Airplane> airplanes, AirplaneConvertCriteriaDTO convertCriteria);

	AirplaneDTO getAirplaneDTOById(Integer airplaneId);







}





