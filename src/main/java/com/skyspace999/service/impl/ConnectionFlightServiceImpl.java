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
import com.skyspace999.dao.ConnectionFlightDAO;
import com.skyspace999.domain.ConnectionFlight;
import com.skyspace999.dto.ConnectionFlightDTO;
import com.skyspace999.dto.ConnectionFlightSearchDTO;
import com.skyspace999.dto.ConnectionFlightPageDTO;
import com.skyspace999.dto.ConnectionFlightConvertCriteriaDTO;
import com.skyspace999.dto.common.RequestDTO;
import com.skyspace999.dto.common.ResultDTO;
import com.skyspace999.service.ConnectionFlightService;
import com.skyspace999.util.ControllerUtils;





@Service
public class ConnectionFlightServiceImpl extends GenericServiceImpl<ConnectionFlight, Integer> implements ConnectionFlightService {

    private final static Logger logger = LoggerFactory.getLogger(ConnectionFlightServiceImpl.class);

	@Autowired
	ConnectionFlightDAO connectionFlightDao;

	


	@Override
	public GenericDAO<ConnectionFlight, Integer> getDAO() {
		return (GenericDAO<ConnectionFlight, Integer>) connectionFlightDao;
	}
	
	public List<ConnectionFlight> findAll () {
		List<ConnectionFlight> connectionFlights = connectionFlightDao.findAll();
		
		return connectionFlights;	
		
	}

	public ResultDTO addConnectionFlight(ConnectionFlightDTO connectionFlightDTO, RequestDTO requestDTO) {

		ConnectionFlight connectionFlight = new ConnectionFlight();

		connectionFlight.setConnectionFlightId(connectionFlightDTO.getConnectionFlightId());


		connectionFlight.setLayoverDuration(connectionFlightDTO.getLayoverDuration());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		connectionFlight = connectionFlightDao.save(connectionFlight);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<ConnectionFlight> getAllConnectionFlights(Pageable pageable) {
		return connectionFlightDao.findAll(pageable);
	}

	public Page<ConnectionFlight> getAllConnectionFlights(Specification<ConnectionFlight> spec, Pageable pageable) {
		return connectionFlightDao.findAll(spec, pageable);
	}

	public ResponseEntity<ConnectionFlightPageDTO> getConnectionFlights(ConnectionFlightSearchDTO connectionFlightSearchDTO) {
	
			Integer connectionFlightId = connectionFlightSearchDTO.getConnectionFlightId(); 
  			String sortBy = connectionFlightSearchDTO.getSortBy();
			String sortOrder = connectionFlightSearchDTO.getSortOrder();
			String searchQuery = connectionFlightSearchDTO.getSearchQuery();
			Integer page = connectionFlightSearchDTO.getPage();
			Integer size = connectionFlightSearchDTO.getSize();

	        Specification<ConnectionFlight> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, connectionFlightId, "connectionFlightId"); 
			
			

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

		Page<ConnectionFlight> connectionFlights = this.getAllConnectionFlights(spec, pageable);
		
		//System.out.println(String.valueOf(connectionFlights.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(connectionFlights.getTotalPages()));
		
		List<ConnectionFlight> connectionFlightsList = connectionFlights.getContent();
		
		ConnectionFlightConvertCriteriaDTO convertCriteria = new ConnectionFlightConvertCriteriaDTO();
		List<ConnectionFlightDTO> connectionFlightDTOs = this.convertConnectionFlightsToConnectionFlightDTOs(connectionFlightsList,convertCriteria);
		
		ConnectionFlightPageDTO connectionFlightPageDTO = new ConnectionFlightPageDTO();
		connectionFlightPageDTO.setConnectionFlights(connectionFlightDTOs);
		connectionFlightPageDTO.setTotalElements(connectionFlights.getTotalElements());
		return ResponseEntity.ok(connectionFlightPageDTO);
	}

	public List<ConnectionFlightDTO> convertConnectionFlightsToConnectionFlightDTOs(List<ConnectionFlight> connectionFlights, ConnectionFlightConvertCriteriaDTO convertCriteria) {
		
		List<ConnectionFlightDTO> connectionFlightDTOs = new ArrayList<ConnectionFlightDTO>();
		
		for (ConnectionFlight connectionFlight : connectionFlights) {
			connectionFlightDTOs.add(convertConnectionFlightToConnectionFlightDTO(connectionFlight,convertCriteria));
		}
		
		return connectionFlightDTOs;

	}
	
	public ConnectionFlightDTO convertConnectionFlightToConnectionFlightDTO(ConnectionFlight connectionFlight, ConnectionFlightConvertCriteriaDTO convertCriteria) {
		
		ConnectionFlightDTO connectionFlightDTO = new ConnectionFlightDTO();
		
		connectionFlightDTO.setConnectionFlightId(connectionFlight.getConnectionFlightId());

	
		connectionFlightDTO.setLayoverDuration(connectionFlight.getLayoverDuration());

	

		
		return connectionFlightDTO;
	}

	public ResultDTO updateConnectionFlight(ConnectionFlightDTO connectionFlightDTO, RequestDTO requestDTO) {
		
		ConnectionFlight connectionFlight = connectionFlightDao.getById(connectionFlightDTO.getConnectionFlightId());

		connectionFlight.setConnectionFlightId(ControllerUtils.setValue(connectionFlight.getConnectionFlightId(), connectionFlightDTO.getConnectionFlightId()));

		connectionFlight.setLayoverDuration(ControllerUtils.setValue(connectionFlight.getLayoverDuration(), connectionFlightDTO.getLayoverDuration()));



        connectionFlight = connectionFlightDao.save(connectionFlight);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ConnectionFlightDTO getConnectionFlightDTOById(Integer connectionFlightId) {
	
		ConnectionFlight connectionFlight = connectionFlightDao.getById(connectionFlightId);
			
		
		ConnectionFlightConvertCriteriaDTO convertCriteria = new ConnectionFlightConvertCriteriaDTO();
		return(this.convertConnectionFlightToConnectionFlightDTO(connectionFlight,convertCriteria));
	}







}
