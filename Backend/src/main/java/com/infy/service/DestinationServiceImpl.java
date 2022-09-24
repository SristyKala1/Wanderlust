package com.infy.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.dto.DestinationDTO;
import com.infy.dto.DetailsDTO;
import com.infy.dto.ItineraryDTO;
import com.infy.entity.Destination;
import com.infy.exception.WanderLustException;
import com.infy.repository.DestinationRepository;

@Service(value="destinationService")
@Transactional
public class DestinationServiceImpl implements DestinationService {
	
	@Autowired
	DestinationRepository destinationRepository;
	
	//Methord to Search the Destination or packages
	public List<DestinationDTO> getDestination(String continent) throws Exception{
		
		List<Destination> destcontinent=destinationRepository.findByContinentIgnoreCase(continent);
		List<DestinationDTO> destinationDTOList=new ArrayList<DestinationDTO>();
		if(destcontinent.isEmpty()) {
			throw new WanderLustException("DestinationService.NO_DESTINATIONS_PRESENT");
		}
		else {
		
			for(Destination e:destcontinent) {
				DestinationDTO destinationDTO=new DestinationDTO();
				destinationDTO.setDestinationId(e.getDestinationId());
				destinationDTO.setContinent(e.getContinent());
				destinationDTO.setAvailability(e.getAvailability());
				destinationDTO.setChargePerPerson(e.getChargePerPerson());
				destinationDTO.setDestinationName(e.getDestinationName());
				destinationDTO.setDiscount(e.getDiscount());
				destinationDTO.setFlightCharge(e.getFlightCharge());
				destinationDTO.setImageUrl(e.getImageUrl());
				destinationDTO.setNoOfNights(e.getNoOfNights());
				destinationDTO.setRating(e.getRating());
				destinationDTO.setNoOfRating(e.getNoOfRating());
					DetailsDTO detailsDTO=new DetailsDTO();
					detailsDTO.setDetailsId(e.getDetailsEntity().getDetailsId());
					detailsDTO.setAbout(e.getDetailsEntity().getAbout());
					detailsDTO.setHighlights(e.getDetailsEntity().getHighlights());
					detailsDTO.setPace(e.getDetailsEntity().getPace());
					detailsDTO.setPackageInclusion(e.getDetailsEntity().getPackageInclusion());
						ItineraryDTO itineraryDTO=new ItineraryDTO();
						itineraryDTO.setFirstDay(e.getDetailsEntity().getItineraryEntity().getFirstDay());
						itineraryDTO.setItineraryId(e.getDetailsEntity().getItineraryEntity().getItineraryId());
						itineraryDTO.setLastDay(e.getDetailsEntity().getItineraryEntity().getLastDay());
						itineraryDTO.setRestOfDays(e.getDetailsEntity().getItineraryEntity().getRestOfDays());
						
					detailsDTO.setItinerary(itineraryDTO);
					destinationDTO.setDetailsDTO(detailsDTO);
					destinationDTOList.add(destinationDTO);
			}
		}
		
		return destinationDTOList;
	}
	
	public List<DestinationDTO> getHotDeals()throws Exception{
		Float val=0.0f;
		List<Destination> destinationList=destinationRepository.findByDiscountGreaterThan(val);
		List<DestinationDTO> destinationDTOList=new ArrayList<DestinationDTO>();;
		if(!destinationList.isEmpty()) {
			
			for(Destination e:destinationList) {
				DestinationDTO destinationDTO=new DestinationDTO();
				destinationDTO.setDestinationId(e.getDestinationId());
				destinationDTO.setContinent(e.getContinent());
				destinationDTO.setAvailability(e.getAvailability());
				destinationDTO.setChargePerPerson(e.getChargePerPerson());
				destinationDTO.setDestinationName(e.getDestinationName());
				destinationDTO.setDiscount(e.getDiscount());
				destinationDTO.setFlightCharge(e.getFlightCharge());
				destinationDTO.setImageUrl(e.getImageUrl());
				destinationDTO.setNoOfNights(e.getNoOfNights());
				destinationDTO.setRating(e.getRating());
				destinationDTO.setNoOfRating(e.getNoOfRating());
					DetailsDTO detailsDTO=new DetailsDTO();
					detailsDTO.setDetailsId(e.getDetailsEntity().getDetailsId());
					detailsDTO.setAbout(e.getDetailsEntity().getAbout());
					detailsDTO.setHighlights(e.getDetailsEntity().getHighlights());
					detailsDTO.setPace(e.getDetailsEntity().getPace());
					detailsDTO.setPackageInclusion(e.getDetailsEntity().getPackageInclusion());
						ItineraryDTO itineraryDTO=new ItineraryDTO();
						itineraryDTO.setFirstDay(e.getDetailsEntity().getItineraryEntity().getFirstDay());
						itineraryDTO.setItineraryId(e.getDetailsEntity().getItineraryEntity().getItineraryId());
						itineraryDTO.setLastDay(e.getDetailsEntity().getItineraryEntity().getLastDay());
						itineraryDTO.setRestOfDays(e.getDetailsEntity().getItineraryEntity().getRestOfDays());
						detailsDTO.setItinerary(itineraryDTO);
						destinationDTO.setDetailsDTO(detailsDTO);
				destinationDTOList.add(destinationDTO);
			}
		}
		System.out.println(destinationDTOList);
		return destinationDTOList;

	}
}
