package com.infy.service;

import java.util.List;

import com.infy.dto.DestinationDTO;

public interface DestinationService {

	public List<DestinationDTO> getDestination(String continent) throws Exception;
	
	public List<DestinationDTO> getHotDeals() throws Exception;
	
}
