package com.infy.service.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.infy.dto.BookingDTO;
import com.infy.dto.DestinationDTO;
import com.infy.entity.Booking;
import com.infy.exception.WanderLustException;
import com.infy.repository.BookingRepository;
import com.infy.repository.DestinationRepository;
import com.infy.service.BookingServiceImpl;

@SpringBootTest
public class BookingServiceTest {

	@Mock
	DestinationRepository destRepository;
	
	@Mock
	BookingRepository bookingRepository;
	
	@InjectMocks
	BookingServiceImpl bookingServiceImpl;
	
	@Test
	public void getValidDateNotFoundTest() throws Exception{
		
		LocalDate date = LocalDate.now().minusDays(5);
		LocalDateTime dateTime = LocalDateTime.now();
		BookingDTO bookDTO = new BookingDTO();
		
		bookDTO.setCheckIn(date);
		bookDTO.setTimeOfBooking(dateTime);
		bookDTO.setNoOfPeople(5);
		bookDTO.setBookingId(101);
		Integer userId=101;
		String destinationId = "D101";
		
		Mockito.when(destRepository.findByDestinationId(Mockito.anyString())).thenReturn(null);
		WanderLustException exception = Assertions.assertThrows(WanderLustException.class, 
				() -> bookingServiceImpl.addBooking(bookDTO,userId,destinationId));
		Assertions.assertEquals("BookingService.INVALID_DATE", exception.getMessage());
		
	}
	
	@Test
	public void getBookingRecoedsNoBookingRecordsFoundTest() throws Exception {
		
		List<Booking> list = new ArrayList<>();
		Integer userId = 101;
		
		Mockito.when(bookingRepository.findByBookUserId(Mockito.anyInt())).thenReturn(null);
		WanderLustException exception = Assertions.assertThrows(WanderLustException.class,
				() -> bookingServiceImpl.getViewBooking(userId));
		Assertions.assertEquals("BookingService.BOOKINGS_NOT_FOUND", exception.getMessage());
	}
	
//	public void deleteBookingFoundTest() throws Exception {
//		Integer userId = 105;
//		
//		Mockito.when(bookingRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
//		WanderLustException exception = Assertions.assertThrows(WanderLustException.class, 
//				() -> bookingServiceImpl.cancelBooking(userId));
//		Assertions.assertEquals("BookingService.BOOKING_NOT_FOUND", exception.getMessage());
//	}
}