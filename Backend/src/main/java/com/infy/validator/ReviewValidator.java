package com.infy.validator;

import com.infy.dto.ReviewDTO;
import com.infy.exception.WanderLustException;

public class ReviewValidator {
	

	public static void validatorUserId(Integer userId) throws WanderLustException {
		if(!validateUserId(userId)) {
			throw new WanderLustException("ReviewValidator.INVALID_USER_ID");
		}
	}
	public static boolean validateUserId(Integer userId) {
		if(userId == null)
			return false;	
		return true;
	}
	
	
	public static void validateReview(ReviewDTO reviewDTO) throws WanderLustException {
		if(!validateRating(reviewDTO.getRating()))
			throw new WanderLustException("ReviewValidator.INVALID_REVIEW");
		if(!validateBookingId(reviewDTO.getBooking().getBookingId()))
			throw new WanderLustException("ReviewValidator.INVALID_BOOKING_ID");
	}
	
	public static boolean validateRating(Integer rating) {
		if(rating == null)
			return false;
		if(rating < 1 || rating >  5)
			return false;
		return true;
	}
	
	public static boolean validateBookingId(Integer bookingId) {
		
		if(bookingId == null)
			return false;
		return true;
	}
}
