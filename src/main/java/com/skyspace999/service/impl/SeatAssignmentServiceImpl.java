package com.skyspace999.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.skyspace999.dao.GenericDAO;
import com.skyspace999.service.GenericService;
import com.skyspace999.service.impl.GenericServiceImpl;
import com.skyspace999.dao.SeatAssignmentDAO;
import com.skyspace999.domain.SeatAssignment;
import com.skyspace999.dto.SeatAssignmentDTO;
import com.skyspace999.dto.SeatAssignmentSearchDTO;
import com.skyspace999.dto.SeatAssignmentPageDTO;
import com.skyspace999.dto.SeatAssignmentConvertCriteriaDTO;
import com.skyspace999.dto.common.RequestDTO;
import com.skyspace999.dto.common.ResultDTO;
import com.skyspace999.service.SeatAssignmentService;
import com.skyspace999.util.ControllerUtils;





@Service
public class SeatAssignmentServiceImpl extends GenericServiceImpl<SeatAssignment, Integer> implements SeatAssignmentService {

    private final static Logger logger = LoggerFactory.getLogger(SeatAssignmentServiceImpl.class);

	@Autowired
	SeatAssignmentDAO seatAssignmentDao;

	


	@Override
	public GenericDAO<SeatAssignment, Integer> getDAO() {
		return (GenericDAO<SeatAssignment, Integer>) seatAssignmentDao;
	}
	
	public List<SeatAssignment> findAll () {
		List<SeatAssignment> seatAssignments = seatAssignmentDao.findAll();
		
		return seatAssignments;	
		
	}

	public ResultDTO addSeatAssignment(SeatAssignmentDTO seatAssignmentDTO, RequestDTO requestDTO) {

		SeatAssignment seatAssignment = new SeatAssignment();

		seatAssignment.setSeatAssignmentId(seatAssignmentDTO.getSeatAssignmentId());


		seatAssignment.setSeatNumber(seatAssignmentDTO.getSeatNumber());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		seatAssignment = seatAssignmentDao.save(seatAssignment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<SeatAssignment> getAllSeatAssignments(Pageable pageable) {
		return seatAssignmentDao.findAll(pageable);
	}

	public Page<SeatAssignment> getAllSeatAssignments(Specification<SeatAssignment> spec, Pageable pageable) {
		return seatAssignmentDao.findAll(spec, pageable);
	}

	public ResponseEntity<SeatAssignmentPageDTO> getSeatAssignments(SeatAssignmentSearchDTO seatAssignmentSearchDTO) {
	
			Integer seatAssignmentId = seatAssignmentSearchDTO.getSeatAssignmentId(); 
 			String seatNumber = seatAssignmentSearchDTO.getSeatNumber(); 
 			String sortBy = seatAssignmentSearchDTO.getSortBy();
			String sortOrder = seatAssignmentSearchDTO.getSortOrder();
			String searchQuery = seatAssignmentSearchDTO.getSearchQuery();
			Integer page = seatAssignmentSearchDTO.getPage();
			Integer size = seatAssignmentSearchDTO.getSize();

	        Specification<SeatAssignment> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, seatAssignmentId, "seatAssignmentId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, seatNumber, "seatNumber"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("seatNumber")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<SeatAssignment> seatAssignments = this.getAllSeatAssignments(spec, pageable);
		
		//System.out.println(String.valueOf(seatAssignments.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(seatAssignments.getTotalPages()));
		
		List<SeatAssignment> seatAssignmentsList = seatAssignments.getContent();
		
		SeatAssignmentConvertCriteriaDTO convertCriteria = new SeatAssignmentConvertCriteriaDTO();
		List<SeatAssignmentDTO> seatAssignmentDTOs = this.convertSeatAssignmentsToSeatAssignmentDTOs(seatAssignmentsList,convertCriteria);
		
		SeatAssignmentPageDTO seatAssignmentPageDTO = new SeatAssignmentPageDTO();
		seatAssignmentPageDTO.setSeatAssignments(seatAssignmentDTOs);
		seatAssignmentPageDTO.setTotalElements(seatAssignments.getTotalElements());
		return ResponseEntity.ok(seatAssignmentPageDTO);
	}

	public List<SeatAssignmentDTO> convertSeatAssignmentsToSeatAssignmentDTOs(List<SeatAssignment> seatAssignments, SeatAssignmentConvertCriteriaDTO convertCriteria) {
		
		List<SeatAssignmentDTO> seatAssignmentDTOs = new ArrayList<SeatAssignmentDTO>();
		
		for (SeatAssignment seatAssignment : seatAssignments) {
			seatAssignmentDTOs.add(convertSeatAssignmentToSeatAssignmentDTO(seatAssignment,convertCriteria));
		}
		
		return seatAssignmentDTOs;

	}
	
	public SeatAssignmentDTO convertSeatAssignmentToSeatAssignmentDTO(SeatAssignment seatAssignment, SeatAssignmentConvertCriteriaDTO convertCriteria) {
		
		SeatAssignmentDTO seatAssignmentDTO = new SeatAssignmentDTO();
		
		seatAssignmentDTO.setSeatAssignmentId(seatAssignment.getSeatAssignmentId());

	
		seatAssignmentDTO.setSeatNumber(seatAssignment.getSeatNumber());

	

		
		return seatAssignmentDTO;
	}

	public ResultDTO updateSeatAssignment(SeatAssignmentDTO seatAssignmentDTO, RequestDTO requestDTO) {
		
		SeatAssignment seatAssignment = seatAssignmentDao.getById(seatAssignmentDTO.getSeatAssignmentId());

		seatAssignment.setSeatAssignmentId(ControllerUtils.setValue(seatAssignment.getSeatAssignmentId(), seatAssignmentDTO.getSeatAssignmentId()));

		seatAssignment.setSeatNumber(ControllerUtils.setValue(seatAssignment.getSeatNumber(), seatAssignmentDTO.getSeatNumber()));



        seatAssignment = seatAssignmentDao.save(seatAssignment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public SeatAssignmentDTO getSeatAssignmentDTOById(Integer seatAssignmentId) {
	
		SeatAssignment seatAssignment = seatAssignmentDao.getById(seatAssignmentId);
			
		
		SeatAssignmentConvertCriteriaDTO convertCriteria = new SeatAssignmentConvertCriteriaDTO();
		return(this.convertSeatAssignmentToSeatAssignmentDTO(seatAssignment,convertCriteria));
	}







}
