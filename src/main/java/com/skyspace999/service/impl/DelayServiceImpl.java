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
import com.skyspace999.dao.DelayDAO;
import com.skyspace999.domain.Delay;
import com.skyspace999.dto.DelayDTO;
import com.skyspace999.dto.DelaySearchDTO;
import com.skyspace999.dto.DelayPageDTO;
import com.skyspace999.dto.DelayConvertCriteriaDTO;
import com.skyspace999.dto.common.RequestDTO;
import com.skyspace999.dto.common.ResultDTO;
import com.skyspace999.service.DelayService;
import com.skyspace999.util.ControllerUtils;





@Service
public class DelayServiceImpl extends GenericServiceImpl<Delay, Integer> implements DelayService {

    private final static Logger logger = LoggerFactory.getLogger(DelayServiceImpl.class);

	@Autowired
	DelayDAO delayDao;

	


	@Override
	public GenericDAO<Delay, Integer> getDAO() {
		return (GenericDAO<Delay, Integer>) delayDao;
	}
	
	public List<Delay> findAll () {
		List<Delay> delays = delayDao.findAll();
		
		return delays;	
		
	}

	public ResultDTO addDelay(DelayDTO delayDTO, RequestDTO requestDTO) {

		Delay delay = new Delay();

		delay.setDelayId(delayDTO.getDelayId());


		delay.setReason(delayDTO.getReason());


		delay.setDuration(delayDTO.getDuration());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		delay = delayDao.save(delay);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Delay> getAllDelays(Pageable pageable) {
		return delayDao.findAll(pageable);
	}

	public Page<Delay> getAllDelays(Specification<Delay> spec, Pageable pageable) {
		return delayDao.findAll(spec, pageable);
	}

	public ResponseEntity<DelayPageDTO> getDelays(DelaySearchDTO delaySearchDTO) {
	
			Integer delayId = delaySearchDTO.getDelayId(); 
 			String reason = delaySearchDTO.getReason(); 
  			String sortBy = delaySearchDTO.getSortBy();
			String sortOrder = delaySearchDTO.getSortOrder();
			String searchQuery = delaySearchDTO.getSearchQuery();
			Integer page = delaySearchDTO.getPage();
			Integer size = delaySearchDTO.getSize();

	        Specification<Delay> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, delayId, "delayId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, reason, "reason"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("reason")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Delay> delays = this.getAllDelays(spec, pageable);
		
		//System.out.println(String.valueOf(delays.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(delays.getTotalPages()));
		
		List<Delay> delaysList = delays.getContent();
		
		DelayConvertCriteriaDTO convertCriteria = new DelayConvertCriteriaDTO();
		List<DelayDTO> delayDTOs = this.convertDelaysToDelayDTOs(delaysList,convertCriteria);
		
		DelayPageDTO delayPageDTO = new DelayPageDTO();
		delayPageDTO.setDelays(delayDTOs);
		delayPageDTO.setTotalElements(delays.getTotalElements());
		return ResponseEntity.ok(delayPageDTO);
	}

	public List<DelayDTO> convertDelaysToDelayDTOs(List<Delay> delays, DelayConvertCriteriaDTO convertCriteria) {
		
		List<DelayDTO> delayDTOs = new ArrayList<DelayDTO>();
		
		for (Delay delay : delays) {
			delayDTOs.add(convertDelayToDelayDTO(delay,convertCriteria));
		}
		
		return delayDTOs;

	}
	
	public DelayDTO convertDelayToDelayDTO(Delay delay, DelayConvertCriteriaDTO convertCriteria) {
		
		DelayDTO delayDTO = new DelayDTO();
		
		delayDTO.setDelayId(delay.getDelayId());

	
		delayDTO.setReason(delay.getReason());

	
		delayDTO.setDuration(delay.getDuration());

	

		
		return delayDTO;
	}

	public ResultDTO updateDelay(DelayDTO delayDTO, RequestDTO requestDTO) {
		
		Delay delay = delayDao.getById(delayDTO.getDelayId());

		delay.setDelayId(ControllerUtils.setValue(delay.getDelayId(), delayDTO.getDelayId()));

		delay.setReason(ControllerUtils.setValue(delay.getReason(), delayDTO.getReason()));

		delay.setDuration(ControllerUtils.setValue(delay.getDuration(), delayDTO.getDuration()));



        delay = delayDao.save(delay);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public DelayDTO getDelayDTOById(Integer delayId) {
	
		Delay delay = delayDao.getById(delayId);
			
		
		DelayConvertCriteriaDTO convertCriteria = new DelayConvertCriteriaDTO();
		return(this.convertDelayToDelayDTO(delay,convertCriteria));
	}







}
