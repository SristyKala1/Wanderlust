package com.infy.validator;

import com.infy.dto.BookingDTO;
import com.infy.entity.Booking;
import java.time.LocalDate;

public class BookingValidator {
	//Exception Throwing
	public static void validateBooking(BookingDTO book) throws Exception {
		if(!validateNumberOfTravellers(book.getNoOfPeople())) {
			throw new Exception("BookingValidator.INVALID_NUMBER_OF_TRAVELLER");
		}
		if(!validateTripStartDate(book.getCheckIn())) {
			throw new Exception("BookingValidator.INVALID_TRIP_START_DATE");
		}
	}
	//Validate Date
	public static boolean validateTripStartDate(LocalDate checkIn) {
		if(checkIn == null)
			return false;
		LocalDate today=LocalDate.now();
		if(today.isBefore(checkIn)) {
			return true;
		}
		else {
		return false;
		}
	}
	//Validate No. of traveller's
	public static boolean validateNumberOfTravellers(Integer noOfPeople) {
		if(noOfPeople == null)
			return false;
		if(noOfPeople>=1 && noOfPeople<=5) {
			return true;
		}
		else {
			return false;
		}
	}
}
