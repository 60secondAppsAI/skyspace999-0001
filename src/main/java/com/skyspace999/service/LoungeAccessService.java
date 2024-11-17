package com.skyspace999.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace999.domain.LoungeAccess;
import com.skyspace999.dto.LoungeAccessDTO;
import com.skyspace999.dto.LoungeAccessSearchDTO;
import com.skyspace999.dto.LoungeAccessPageDTO;
import com.skyspace999.dto.LoungeAccessConvertCriteriaDTO;
import com.skyspace999.service.GenericService;
import com.skyspace999.dto.common.RequestDTO;
import com.skyspace999.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface LoungeAccessService extends GenericService<LoungeAccess, Integer> {

	List<LoungeAccess> findAll();

	ResultDTO addLoungeAccess(LoungeAccessDTO loungeAccessDTO, RequestDTO requestDTO);

	ResultDTO updateLoungeAccess(LoungeAccessDTO loungeAccessDTO, RequestDTO requestDTO);

    Page<LoungeAccess> getAllLoungeAccesss(Pageable pageable);

    Page<LoungeAccess> getAllLoungeAccesss(Specification<LoungeAccess> spec, Pageable pageable);

	ResponseEntity<LoungeAccessPageDTO> getLoungeAccesss(LoungeAccessSearchDTO loungeAccessSearchDTO);
	
	List<LoungeAccessDTO> convertLoungeAccesssToLoungeAccessDTOs(List<LoungeAccess> loungeAccesss, LoungeAccessConvertCriteriaDTO convertCriteria);

	LoungeAccessDTO getLoungeAccessDTOById(Integer loungeAccessId);







}





