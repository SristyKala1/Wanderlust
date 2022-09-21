package com.infy.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.io.Exception;

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
import com.infy.repository.BookingRepository;
import com.infy.repository.DestinationRepository;
import com.infy.repository.UserRepository;


@Service(value = "bookingService")
@Transactional
public class BookingServiceImpl implements BookingService{

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DestinationRepository destinationRepository;
	
	
	@Override
	public Integer addBooking(BookingDTO bookingDTO,Integer userId,String destinationId) throws Exception {
		
	}
	
	public List<BookingDTO> getViewBooking(Integer userId) throws Exception {
		
		
	}
	
	public Float cancelBooking(Integer bookingId) throws WanderLustException {
		
		
	}
	
	public DestinationDTO getDestinationDetails(String destinationId) throws Exception {
		
	}
	
}
