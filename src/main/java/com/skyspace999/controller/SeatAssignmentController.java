package com.skyspace999.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.skyspace999.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.skyspace999.domain.SeatAssignment;
import com.skyspace999.dto.SeatAssignmentDTO;
import com.skyspace999.dto.SeatAssignmentSearchDTO;
import com.skyspace999.dto.SeatAssignmentPageDTO;
import com.skyspace999.service.SeatAssignmentService;
import com.skyspace999.dto.common.RequestDTO;
import com.skyspace999.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/seatAssignment")
@RestController
public class SeatAssignmentController {

	private final static Logger logger = LoggerFactory.getLogger(SeatAssignmentController.class);

	@Autowired
	SeatAssignmentService seatAssignmentService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<SeatAssignment> getAll() {

		List<SeatAssignment> seatAssignments = seatAssignmentService.findAll();
		
		return seatAssignments;	
	}

	@GetMapping(value = "/{seatAssignmentId}")
	@ResponseBody
	public SeatAssignmentDTO getSeatAssignment(@PathVariable Integer seatAssignmentId) {
		
		return (seatAssignmentService.getSeatAssignmentDTOById(seatAssignmentId));
	}

 	@RequestMapping(value = "/addSeatAssignment", method = RequestMethod.POST)
	public ResponseEntity<?> addSeatAssignment(@RequestBody SeatAssignmentDTO seatAssignmentDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = seatAssignmentService.addSeatAssignment(seatAssignmentDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/seatAssignments")
	public ResponseEntity<SeatAssignmentPageDTO> getSeatAssignments(SeatAssignmentSearchDTO seatAssignmentSearchDTO) {
 
		return seatAssignmentService.getSeatAssignments(seatAssignmentSearchDTO);
	}	

	@RequestMapping(value = "/updateSeatAssignment", method = RequestMethod.POST)
	public ResponseEntity<?> updateSeatAssignment(@RequestBody SeatAssignmentDTO seatAssignmentDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = seatAssignmentService.updateSeatAssignment(seatAssignmentDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
