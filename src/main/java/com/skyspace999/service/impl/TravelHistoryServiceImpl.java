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
import com.skyspace999.dao.TravelHistoryDAO;
import com.skyspace999.domain.TravelHistory;
import com.skyspace999.dto.TravelHistoryDTO;
import com.skyspace999.dto.TravelHistorySearchDTO;
import com.skyspace999.dto.TravelHistoryPageDTO;
import com.skyspace999.dto.TravelHistoryConvertCriteriaDTO;
import com.skyspace999.dto.common.RequestDTO;
import com.skyspace999.dto.common.ResultDTO;
import com.skyspace999.service.TravelHistoryService;
import com.skyspace999.util.ControllerUtils;





@Service
public class TravelHistoryServiceImpl extends GenericServiceImpl<TravelHistory, Integer> implements TravelHistoryService {

    private final static Logger logger = LoggerFactory.getLogger(TravelHistoryServiceImpl.class);

	@Autowired
	TravelHistoryDAO travelHistoryDao;

	


	@Override
	public GenericDAO<TravelHistory, Integer> getDAO() {
		return (GenericDAO<TravelHistory, Integer>) travelHistoryDao;
	}
	
	public List<TravelHistory> findAll () {
		List<TravelHistory> travelHistorys = travelHistoryDao.findAll();
		
		return travelHistorys;	
		
	}

	public ResultDTO addTravelHistory(TravelHistoryDTO travelHistoryDTO, RequestDTO requestDTO) {

		TravelHistory travelHistory = new TravelHistory();

		travelHistory.setTravelHistoryId(travelHistoryDTO.getTravelHistoryId());


		travelHistory.setTravelDate(travelHistoryDTO.getTravelDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		travelHistory = travelHistoryDao.save(travelHistory);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<TravelHistory> getAllTravelHistorys(Pageable pageable) {
		return travelHistoryDao.findAll(pageable);
	}

	public Page<TravelHistory> getAllTravelHistorys(Specification<TravelHistory> spec, Pageable pageable) {
		return travelHistoryDao.findAll(spec, pageable);
	}

	public ResponseEntity<TravelHistoryPageDTO> getTravelHistorys(TravelHistorySearchDTO travelHistorySearchDTO) {
	
			Integer travelHistoryId = travelHistorySearchDTO.getTravelHistoryId(); 
   			String sortBy = travelHistorySearchDTO.getSortBy();
			String sortOrder = travelHistorySearchDTO.getSortOrder();
			String searchQuery = travelHistorySearchDTO.getSearchQuery();
			Integer page = travelHistorySearchDTO.getPage();
			Integer size = travelHistorySearchDTO.getSize();

	        Specification<TravelHistory> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, travelHistoryId, "travelHistoryId"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

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

		Page<TravelHistory> travelHistorys = this.getAllTravelHistorys(spec, pageable);
		
		//System.out.println(String.valueOf(travelHistorys.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(travelHistorys.getTotalPages()));
		
		List<TravelHistory> travelHistorysList = travelHistorys.getContent();
		
		TravelHistoryConvertCriteriaDTO convertCriteria = new TravelHistoryConvertCriteriaDTO();
		List<TravelHistoryDTO> travelHistoryDTOs = this.convertTravelHistorysToTravelHistoryDTOs(travelHistorysList,convertCriteria);
		
		TravelHistoryPageDTO travelHistoryPageDTO = new TravelHistoryPageDTO();
		travelHistoryPageDTO.setTravelHistorys(travelHistoryDTOs);
		travelHistoryPageDTO.setTotalElements(travelHistorys.getTotalElements());
		return ResponseEntity.ok(travelHistoryPageDTO);
	}

	public List<TravelHistoryDTO> convertTravelHistorysToTravelHistoryDTOs(List<TravelHistory> travelHistorys, TravelHistoryConvertCriteriaDTO convertCriteria) {
		
		List<TravelHistoryDTO> travelHistoryDTOs = new ArrayList<TravelHistoryDTO>();
		
		for (TravelHistory travelHistory : travelHistorys) {
			travelHistoryDTOs.add(convertTravelHistoryToTravelHistoryDTO(travelHistory,convertCriteria));
		}
		
		return travelHistoryDTOs;

	}
	
	public TravelHistoryDTO convertTravelHistoryToTravelHistoryDTO(TravelHistory travelHistory, TravelHistoryConvertCriteriaDTO convertCriteria) {
		
		TravelHistoryDTO travelHistoryDTO = new TravelHistoryDTO();
		
		travelHistoryDTO.setTravelHistoryId(travelHistory.getTravelHistoryId());

	
		travelHistoryDTO.setTravelDate(travelHistory.getTravelDate());

	

		
		return travelHistoryDTO;
	}

	public ResultDTO updateTravelHistory(TravelHistoryDTO travelHistoryDTO, RequestDTO requestDTO) {
		
		TravelHistory travelHistory = travelHistoryDao.getById(travelHistoryDTO.getTravelHistoryId());

		travelHistory.setTravelHistoryId(ControllerUtils.setValue(travelHistory.getTravelHistoryId(), travelHistoryDTO.getTravelHistoryId()));

		travelHistory.setTravelDate(ControllerUtils.setValue(travelHistory.getTravelDate(), travelHistoryDTO.getTravelDate()));



        travelHistory = travelHistoryDao.save(travelHistory);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public TravelHistoryDTO getTravelHistoryDTOById(Integer travelHistoryId) {
	
		TravelHistory travelHistory = travelHistoryDao.getById(travelHistoryId);
			
		
		TravelHistoryConvertCriteriaDTO convertCriteria = new TravelHistoryConvertCriteriaDTO();
		return(this.convertTravelHistoryToTravelHistoryDTO(travelHistory,convertCriteria));
	}







}
