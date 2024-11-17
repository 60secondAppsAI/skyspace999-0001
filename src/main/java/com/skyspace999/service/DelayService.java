package com.skyspace999.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace999.domain.Delay;
import com.skyspace999.dto.DelayDTO;
import com.skyspace999.dto.DelaySearchDTO;
import com.skyspace999.dto.DelayPageDTO;
import com.skyspace999.dto.DelayConvertCriteriaDTO;
import com.skyspace999.service.GenericService;
import com.skyspace999.dto.common.RequestDTO;
import com.skyspace999.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface DelayService extends GenericService<Delay, Integer> {

	List<Delay> findAll();

	ResultDTO addDelay(DelayDTO delayDTO, RequestDTO requestDTO);

	ResultDTO updateDelay(DelayDTO delayDTO, RequestDTO requestDTO);

    Page<Delay> getAllDelays(Pageable pageable);

    Page<Delay> getAllDelays(Specification<Delay> spec, Pageable pageable);

	ResponseEntity<DelayPageDTO> getDelays(DelaySearchDTO delaySearchDTO);
	
	List<DelayDTO> convertDelaysToDelayDTOs(List<Delay> delays, DelayConvertCriteriaDTO convertCriteria);

	DelayDTO getDelayDTOById(Integer delayId);







}





