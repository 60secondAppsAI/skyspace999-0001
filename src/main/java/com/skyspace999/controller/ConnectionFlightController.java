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

import com.skyspace999.domain.ConnectionFlight;
import com.skyspace999.dto.ConnectionFlightDTO;
import com.skyspace999.dto.ConnectionFlightSearchDTO;
import com.skyspace999.dto.ConnectionFlightPageDTO;
import com.skyspace999.service.ConnectionFlightService;
import com.skyspace999.dto.common.RequestDTO;
import com.skyspace999.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/connectionFlight")
@RestController
public class ConnectionFlightController {

	private final static Logger logger = LoggerFactory.getLogger(ConnectionFlightController.class);

	@Autowired
	ConnectionFlightService connectionFlightService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<ConnectionFlight> getAll() {

		List<ConnectionFlight> connectionFlights = connectionFlightService.findAll();
		
		return connectionFlights;	
	}

	@GetMapping(value = "/{connectionFlightId}")
	@ResponseBody
	public ConnectionFlightDTO getConnectionFlight(@PathVariable Integer connectionFlightId) {
		
		return (connectionFlightService.getConnectionFlightDTOById(connectionFlightId));
	}

 	@RequestMapping(value = "/addConnectionFlight", method = RequestMethod.POST)
	public ResponseEntity<?> addConnectionFlight(@RequestBody ConnectionFlightDTO connectionFlightDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = connectionFlightService.addConnectionFlight(connectionFlightDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/connectionFlights")
	public ResponseEntity<ConnectionFlightPageDTO> getConnectionFlights(ConnectionFlightSearchDTO connectionFlightSearchDTO) {
 
		return connectionFlightService.getConnectionFlights(connectionFlightSearchDTO);
	}	

	@RequestMapping(value = "/updateConnectionFlight", method = RequestMethod.POST)
	public ResponseEntity<?> updateConnectionFlight(@RequestBody ConnectionFlightDTO connectionFlightDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = connectionFlightService.updateConnectionFlight(connectionFlightDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
