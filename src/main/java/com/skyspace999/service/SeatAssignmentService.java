package com.skyspace999.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace999.domain.SeatAssignment;
import com.skyspace999.dto.SeatAssignmentDTO;
import com.skyspace999.dto.SeatAssignmentSearchDTO;
import com.skyspace999.dto.SeatAssignmentPageDTO;
import com.skyspace999.dto.SeatAssignmentConvertCriteriaDTO;
import com.skyspace999.service.GenericService;
import com.skyspace999.dto.common.RequestDTO;
import com.skyspace999.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SeatAssignmentService extends GenericService<SeatAssignment, Integer> {

	List<SeatAssignment> findAll();

	ResultDTO addSeatAssignment(SeatAssignmentDTO seatAssignmentDTO, RequestDTO requestDTO);

	ResultDTO updateSeatAssignment(SeatAssignmentDTO seatAssignmentDTO, RequestDTO requestDTO);

    Page<SeatAssignment> getAllSeatAssignments(Pageable pageable);

    Page<SeatAssignment> getAllSeatAssignments(Specification<SeatAssignment> spec, Pageable pageable);

	ResponseEntity<SeatAssignmentPageDTO> getSeatAssignments(SeatAssignmentSearchDTO seatAssignmentSearchDTO);
	
	List<SeatAssignmentDTO> convertSeatAssignmentsToSeatAssignmentDTOs(List<SeatAssignment> seatAssignments, SeatAssignmentConvertCriteriaDTO convertCriteria);

	SeatAssignmentDTO getSeatAssignmentDTOById(Integer seatAssignmentId);







}





