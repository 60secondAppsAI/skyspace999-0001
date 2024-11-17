package com.skyspace999.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace999.domain.TravelHistory;
import com.skyspace999.dto.TravelHistoryDTO;
import com.skyspace999.dto.TravelHistorySearchDTO;
import com.skyspace999.dto.TravelHistoryPageDTO;
import com.skyspace999.dto.TravelHistoryConvertCriteriaDTO;
import com.skyspace999.service.GenericService;
import com.skyspace999.dto.common.RequestDTO;
import com.skyspace999.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface TravelHistoryService extends GenericService<TravelHistory, Integer> {

	List<TravelHistory> findAll();

	ResultDTO addTravelHistory(TravelHistoryDTO travelHistoryDTO, RequestDTO requestDTO);

	ResultDTO updateTravelHistory(TravelHistoryDTO travelHistoryDTO, RequestDTO requestDTO);

    Page<TravelHistory> getAllTravelHistorys(Pageable pageable);

    Page<TravelHistory> getAllTravelHistorys(Specification<TravelHistory> spec, Pageable pageable);

	ResponseEntity<TravelHistoryPageDTO> getTravelHistorys(TravelHistorySearchDTO travelHistorySearchDTO);
	
	List<TravelHistoryDTO> convertTravelHistorysToTravelHistoryDTOs(List<TravelHistory> travelHistorys, TravelHistoryConvertCriteriaDTO convertCriteria);

	TravelHistoryDTO getTravelHistoryDTOById(Integer travelHistoryId);







}





