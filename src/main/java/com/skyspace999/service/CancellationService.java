package com.skyspace999.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace999.domain.Cancellation;
import com.skyspace999.dto.CancellationDTO;
import com.skyspace999.dto.CancellationSearchDTO;
import com.skyspace999.dto.CancellationPageDTO;
import com.skyspace999.dto.CancellationConvertCriteriaDTO;
import com.skyspace999.service.GenericService;
import com.skyspace999.dto.common.RequestDTO;
import com.skyspace999.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CancellationService extends GenericService<Cancellation, Integer> {

	List<Cancellation> findAll();

	ResultDTO addCancellation(CancellationDTO cancellationDTO, RequestDTO requestDTO);

	ResultDTO updateCancellation(CancellationDTO cancellationDTO, RequestDTO requestDTO);

    Page<Cancellation> getAllCancellations(Pageable pageable);

    Page<Cancellation> getAllCancellations(Specification<Cancellation> spec, Pageable pageable);

	ResponseEntity<CancellationPageDTO> getCancellations(CancellationSearchDTO cancellationSearchDTO);
	
	List<CancellationDTO> convertCancellationsToCancellationDTOs(List<Cancellation> cancellations, CancellationConvertCriteriaDTO convertCriteria);

	CancellationDTO getCancellationDTOById(Integer cancellationId);







}





