package com.infy.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.dto.DestinationDTO;
import com.infy.service.DestinationService;

@CrossOrigin
@RestController
@RequestMapping("DestinationAPI")
public class DestinationAPI {

	@Autowired
	private DestinationService destinationService;
	
	@Autowired
	private Environment environment;
	
	@GetMapping(value = "/packages/{continent}")
	public ResponseEntity<List<DestinationDTO>> showContinentDetails(@PathVariable String continent) throws Exception  {
		try{
			List<DestinationDTO> destination = destinationService.getDestination(continent);
			return  new ResponseEntity<List<DestinationDTO>>(destination, HttpStatus.OK);
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()));
		}	
	}
	
	@GetMapping(value="/packages")
	public ResponseEntity<List<DestinationDTO>> hotDeals() throws Exception  {
		try{
			List<DestinationDTO> destination = destinationService.getHotDeals();
	
		return new ResponseEntity<List<DestinationDTO>>(destination, HttpStatus.OK);
		
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()));
		}	
	}
}
