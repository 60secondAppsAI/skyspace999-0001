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

import com.skyspace999.domain.TravelHistory;
import com.skyspace999.dto.TravelHistoryDTO;
import com.skyspace999.dto.TravelHistorySearchDTO;
import com.skyspace999.dto.TravelHistoryPageDTO;
import com.skyspace999.service.TravelHistoryService;
import com.skyspace999.dto.common.RequestDTO;
import com.skyspace999.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/travelHistory")
@RestController
public class TravelHistoryController {

	private final static Logger logger = LoggerFactory.getLogger(TravelHistoryController.class);

	@Autowired
	TravelHistoryService travelHistoryService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<TravelHistory> getAll() {

		List<TravelHistory> travelHistorys = travelHistoryService.findAll();
		
		return travelHistorys;	
	}

	@GetMapping(value = "/{travelHistoryId}")
	@ResponseBody
	public TravelHistoryDTO getTravelHistory(@PathVariable Integer travelHistoryId) {
		
		return (travelHistoryService.getTravelHistoryDTOById(travelHistoryId));
	}

 	@RequestMapping(value = "/addTravelHistory", method = RequestMethod.POST)
	public ResponseEntity<?> addTravelHistory(@RequestBody TravelHistoryDTO travelHistoryDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = travelHistoryService.addTravelHistory(travelHistoryDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/travelHistorys")
	public ResponseEntity<TravelHistoryPageDTO> getTravelHistorys(TravelHistorySearchDTO travelHistorySearchDTO) {
 
		return travelHistoryService.getTravelHistorys(travelHistorySearchDTO);
	}	

	@RequestMapping(value = "/updateTravelHistory", method = RequestMethod.POST)
	public ResponseEntity<?> updateTravelHistory(@RequestBody TravelHistoryDTO travelHistoryDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = travelHistoryService.updateTravelHistory(travelHistoryDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
