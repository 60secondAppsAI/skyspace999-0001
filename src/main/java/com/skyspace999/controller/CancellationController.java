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

import com.skyspace999.domain.Cancellation;
import com.skyspace999.dto.CancellationDTO;
import com.skyspace999.dto.CancellationSearchDTO;
import com.skyspace999.dto.CancellationPageDTO;
import com.skyspace999.service.CancellationService;
import com.skyspace999.dto.common.RequestDTO;
import com.skyspace999.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/cancellation")
@RestController
public class CancellationController {

	private final static Logger logger = LoggerFactory.getLogger(CancellationController.class);

	@Autowired
	CancellationService cancellationService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Cancellation> getAll() {

		List<Cancellation> cancellations = cancellationService.findAll();
		
		return cancellations;	
	}

	@GetMapping(value = "/{cancellationId}")
	@ResponseBody
	public CancellationDTO getCancellation(@PathVariable Integer cancellationId) {
		
		return (cancellationService.getCancellationDTOById(cancellationId));
	}

 	@RequestMapping(value = "/addCancellation", method = RequestMethod.POST)
	public ResponseEntity<?> addCancellation(@RequestBody CancellationDTO cancellationDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = cancellationService.addCancellation(cancellationDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/cancellations")
	public ResponseEntity<CancellationPageDTO> getCancellations(CancellationSearchDTO cancellationSearchDTO) {
 
		return cancellationService.getCancellations(cancellationSearchDTO);
	}	

	@RequestMapping(value = "/updateCancellation", method = RequestMethod.POST)
	public ResponseEntity<?> updateCancellation(@RequestBody CancellationDTO cancellationDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = cancellationService.updateCancellation(cancellationDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
