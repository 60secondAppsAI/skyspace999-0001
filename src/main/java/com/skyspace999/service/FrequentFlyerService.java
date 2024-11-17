package com.skyspace999.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace999.domain.FrequentFlyer;
import com.skyspace999.dto.FrequentFlyerDTO;
import com.skyspace999.dto.FrequentFlyerSearchDTO;
import com.skyspace999.dto.FrequentFlyerPageDTO;
import com.skyspace999.dto.FrequentFlyerConvertCriteriaDTO;
import com.skyspace999.service.GenericService;
import com.skyspace999.dto.common.RequestDTO;
import com.skyspace999.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface FrequentFlyerService extends GenericService<FrequentFlyer, Integer> {

	List<FrequentFlyer> findAll();

	ResultDTO addFrequentFlyer(FrequentFlyerDTO frequentFlyerDTO, RequestDTO requestDTO);

	ResultDTO updateFrequentFlyer(FrequentFlyerDTO frequentFlyerDTO, RequestDTO requestDTO);

    Page<FrequentFlyer> getAllFrequentFlyers(Pageable pageable);

    Page<FrequentFlyer> getAllFrequentFlyers(Specification<FrequentFlyer> spec, Pageable pageable);

	ResponseEntity<FrequentFlyerPageDTO> getFrequentFlyers(FrequentFlyerSearchDTO frequentFlyerSearchDTO);
	
	List<FrequentFlyerDTO> convertFrequentFlyersToFrequentFlyerDTOs(List<FrequentFlyer> frequentFlyers, FrequentFlyerConvertCriteriaDTO convertCriteria);

	FrequentFlyerDTO getFrequentFlyerDTOById(Integer frequentFlyerId);







}





