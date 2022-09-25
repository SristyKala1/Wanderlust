package com.infy.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.dto.BookingDTO;
import com.infy.dto.DestinationDTO;
import com.infy.dto.DetailsDTO;
import com.infy.dto.ItineraryDTO;
import com.infy.entity.Booking;
import com.infy.entity.Destination;
import com.infy.entity.Details;
import com.infy.entity.Itinerary;
import com.infy.entity.User;
import com.infy.exception.WanderLustException;
import com.infy.repository.BookingRepository;
import com.infy.repository.DestinationRepository;
import com.infy.repository.UserRepository;
import com.infy.validator.BookingValidator;

@Service(value = "bookingService")
@Transactional
public class BookingServiceImpl implements BookingService{

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DestinationRepository destinationRepository;
	
	
//	@Override
	public Integer addBooking(BookingDTO bookingDTO,Integer userId,String destinationId) throws WanderLustException {
		//BookingValidator.validateBooking(bookingDTO);
		
		try {
		Booking booking = new Booking();
		booking.setCheckIn(bookingDTO.getCheckIn());
		booking.setNoOfPeople(bookingDTO.getNoOfPeople());
		booking.setTotalCost(bookingDTO.getTotalCost());
		LocalDateTime date=LocalDateTime.now();
		booking.setTimeOfBooking(date);
		
		Destination destination=destinationRepository.findByDestinationId(destinationId);
		if(destination==null)
			throw new WanderLustException("BookingService.INVALID_BOOKINGS");
		booking.setCheckOut(bookingDTO.getCheckIn().plusDays(destination.getNoOfNights()));
		Optional<User> optional=userRepository.findById(userId);
		User user=optional.orElseThrow(() -> new WanderLustException("BookingService.INVALID_BOOKINGS"));
		booking.setUserEntity(user);
		if(destination.getAvailability() >= booking.getNoOfPeople()) {
			destination.setAvailability(destination.getAvailability() - booking.getNoOfPeople());
			booking.setDestinationEntity(destination);
		}
		
		bookingRepository.save(booking);
		return booking.getBookingId();
		}
		catch(Exception e)
		{
			throw new WanderLustException("BookingService.INVALID_DATE");
		}
	}
	
	public List<BookingDTO> getViewBooking(Integer userId) throws WanderLustException {
		
		List<Booking> bookingList = bookingRepository.findByBookUserId(userId);
		List<BookingDTO> bookingDtoList = new ArrayList<BookingDTO>();
		if(bookingList.isEmpty()) {
			throw new WanderLustException("BookingService.INVALID_BOOKINGS");
		}
		
		for(Booking booking: bookingList) {
			BookingDTO bookingDTO = new BookingDTO();
			bookingDTO.setBookingId(booking.getBookingId());
			bookingDTO.setCheckIn(booking.getCheckIn());
			bookingDTO.setCheckOut(booking.getCheckOut());
			bookingDTO.setNoOfPeople(booking.getNoOfPeople());
			bookingDTO.setTimeOfBooking(booking.getTimeOfBooking());
			bookingDTO.setTotalCost(booking.getTotalCost());
			
			DestinationDTO destinationDTO = new DestinationDTO();
			destinationDTO.setAvailability(booking.getDestinationEntity().getAvailability());
			destinationDTO.setChargePerPerson(booking.getDestinationEntity().getChargePerPerson());
			destinationDTO.setContinent(booking.getDestinationEntity().getContinent());
			destinationDTO.setDestinationId(booking.getDestinationEntity().getDestinationId());
			destinationDTO.setDestinationName(booking.getDestinationEntity().getDestinationName());
			destinationDTO.setDiscount(booking.getDestinationEntity().getDiscount());
			destinationDTO.setFlightCharge(booking.getDestinationEntity().getFlightCharge());
			destinationDTO.setImageUrl(booking.getDestinationEntity().getImageUrl());
			destinationDTO.setNoOfNights(booking.getDestinationEntity().getNoOfNights());
			
			DetailsDTO detailsDTO = new DetailsDTO();
			detailsDTO.setAbout(booking.getDestinationEntity().getDetailsEntity().getAbout());
			detailsDTO.setDetailsId(booking.getDestinationEntity().getDetailsEntity().getDetailsId());
			detailsDTO.setPace(booking.getDestinationEntity().getDetailsEntity().getPace());
			detailsDTO.setPackageInclusion(booking.getDestinationEntity().getDetailsEntity().getPackageInclusion());
			
			ItineraryDTO itineraryDTO = new ItineraryDTO();
			itineraryDTO.setFirstDay(booking.getDestinationEntity().getDetailsEntity().getItineraryEntity().getFirstDay());
			itineraryDTO.setItineraryId(booking.getDestinationEntity().getDetailsEntity().getItineraryEntity().getItineraryId());
			itineraryDTO.setLastDay(booking.getDestinationEntity().getDetailsEntity().getItineraryEntity().getLastDay());
			itineraryDTO.setRestOfDays(booking.getDestinationEntity().getDetailsEntity().getItineraryEntity().getRestOfDays());
			
			detailsDTO.setItinerary(itineraryDTO);
			destinationDTO.setDetailsDTO(detailsDTO);
			bookingDtoList.add(bookingDTO);
		}
		
		return bookingDtoList;
	}
	
	public Float cancelBooking(Integer bookingId) throws WanderLustException {
		
		Float refundAmount=null;

		Optional<Booking> optional=bookingRepository.findById(bookingId);
		Booking booking=optional.orElseThrow(() -> new WanderLustException("No bookings available"));

		booking.getDestinationEntity().setAvailability(booking.getDestinationEntity().getAvailability()+booking.getNoOfPeople());
		booking.setDestinationEntity(null);
		booking.setUserEntity(null);
		
		refundAmount=booking.getTotalCost();
		bookingRepository.delete(booking);
		return refundAmount;
	}
	
	public DestinationDTO getDestinationDetails(String destinationId) throws WanderLustException {
		
		Destination destination=destinationRepository.findByDestinationId(destinationId);
		DestinationDTO destinationDTO=new DestinationDTO();
			
		if(destination!=null)
		{
			destinationDTO.setAvailability(destination.getAvailability());
			destinationDTO.setChargePerPerson(destination.getChargePerPerson());
			destinationDTO.setContinent(destination.getContinent());
			destinationDTO.setDestinationId(destination.getDestinationId());
			destinationDTO.setDestinationName(destination.getDestinationName());
			destinationDTO.setDiscount(destination.getDiscount());
			destinationDTO.setFlightCharge(destination.getFlightCharge());
			destinationDTO.setImageUrl(destination.getImageUrl());
			destinationDTO.setNoOfNights(destination.getNoOfNights());
			
			Details detailsEntity = destination.getDetailsEntity();
			DetailsDTO detailsDTO = new DetailsDTO();
			detailsDTO.setAbout(detailsEntity.getAbout());
			detailsDTO.setDetailsId(detailsEntity.getDetailsId());
			detailsDTO.setHighlights(detailsEntity.getHighlights());
			detailsDTO.setPace(detailsEntity.getPace());
			detailsDTO.setPackageInclusion(detailsEntity.getPackageInclusion());
			
			Itinerary itineraryEntity = destination.getDetailsEntity().getItineraryEntity();
			ItineraryDTO itineraryDTO = new ItineraryDTO();
			itineraryDTO.setFirstDay(itineraryEntity.getFirstDay());
			itineraryDTO.setLastDay(itineraryEntity.getLastDay());
			itineraryDTO.setItineraryId(itineraryEntity.getItineraryId());
			itineraryDTO.setRestOfDays(itineraryEntity.getRestOfDays());
			
			detailsDTO.setItinerary(itineraryDTO);
			destinationDTO.setDetailsDTO(detailsDTO);
			return destinationDTO;
		}
		else 
			throw new WanderLustException("No destinations available");
	}
	
}
