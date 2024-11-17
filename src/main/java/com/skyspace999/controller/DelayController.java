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

import com.skyspace999.domain.Delay;
import com.skyspace999.dto.DelayDTO;
import com.skyspace999.dto.DelaySearchDTO;
import com.skyspace999.dto.DelayPageDTO;
import com.skyspace999.service.DelayService;
import com.skyspace999.dto.common.RequestDTO;
import com.skyspace999.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/delay")
@RestController
public class DelayController {

	private final static Logger logger = LoggerFactory.getLogger(DelayController.class);

	@Autowired
	DelayService delayService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Delay> getAll() {

		List<Delay> delays = delayService.findAll();
		
		return delays;	
	}

	@GetMapping(value = "/{delayId}")
	@ResponseBody
	public DelayDTO getDelay(@PathVariable Integer delayId) {
		
		return (delayService.getDelayDTOById(delayId));
	}

 	@RequestMapping(value = "/addDelay", method = RequestMethod.POST)
	public ResponseEntity<?> addDelay(@RequestBody DelayDTO delayDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = delayService.addDelay(delayDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/delays")
	public ResponseEntity<DelayPageDTO> getDelays(DelaySearchDTO delaySearchDTO) {
 
		return delayService.getDelays(delaySearchDTO);
	}	

	@RequestMapping(value = "/updateDelay", method = RequestMethod.POST)
	public ResponseEntity<?> updateDelay(@RequestBody DelayDTO delayDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = delayService.updateDelay(delayDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
