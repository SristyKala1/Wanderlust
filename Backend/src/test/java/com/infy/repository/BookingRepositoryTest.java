//package com.infy.repository.test;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import com.infy.dto.BookingDTO;
//import com.infy.entity.Booking;
//import com.infy.entity.Destination;
//import com.infy.entity.User;
//import com.infy.exception.WanderLustException;
//import com.infy.repository.BookingRepository;
//
//@DataJpaTest
//public class BookingRepositoryTest {
//	
//	
//@Autowired
//private BookingRepository bookingRepository;
//
//
//private Booking booking;
//
//
//@BeforeEach
//public void setUp(){
//	booking =new Booking();
//	booking.setBookingId(1001);
//	booking.setCheckIn(LocalDate.now().plusDays(1));
//	booking.setCheckOut(LocalDate.now().plusDays(7));
//	booking.setNoOfPeople(2);
//	booking.setTimeOfBooking(LocalDateTime.now());
//	booking.setTotalCost(4356.0f);
//	Destination dest=new Destination();
//	dest.setDestinationId("D1001");
//	booking.setDestinationEntity(dest);
//	User user=new User();
//	user.setUserId(101);
//}
//
//@Test
//public void validAddBooking(BookingDTO bookingDTO) throws Exception{
//	bookingRepository.save(booking);
//	Assertions.assertNotNull(bookingRepository.findByBookUserId(101));
//}
//
//@Test 
//public void invalidAddBooking(BookingDTO bookingDTO) throws Exception{
//	bookingRepository.save(booking);
//	Assertions.assertNotNull(bookingRepository.findByBookUserId(106));
//}
//	
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
