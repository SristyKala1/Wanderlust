package com.infy.service;

import java.util.List;

import com.infy.dto.BookingDTO;
import com.infy.dto.DestinationDTO;
import java.io.*;

public interface BookingService {
	
	public Integer addBooking(BookingDTO bookingDTO, Integer userId,String destinationId) throws Exception;
	
	public List<BookingDTO> getViewBooking(Integer userId) throws Exception;
	
	public Float cancelBooking(Integer bookingId) throws Exception;
	
	public DestinationDTO getDestinationDetails(String destinationId) throws Exception;

}
