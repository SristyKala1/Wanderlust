package com.infy.validator.test;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import com.infy.dto.BookingDTO;
import com.infy.dto.ReviewDTO;
import com.infy.exception.WanderLustException;
import com.infy.validator.ReviewValidator;

public class ReviewValidatorTest {
	@Rule
	public ExpectedException ee = ExpectedException.none();
	
	@Test
	public void validateUserIdInvalidTest(){
	Boolean result = ReviewValidator.validateUserId(null);
	Assert.assertFalse(result);	
	}
	
	@Test
	public void validateUserIdValidTest(){
	Boolean result = ReviewValidator.validateUserId(101);
	Assert.assertTrue(result);	
	}
	
	@Test
	public void validateRatingInvalidTest(){
	Boolean result = ReviewValidator.validateRating(null);
	Assert.assertFalse(result);	
	}
	
	@Test
	public void validateRatingInvalidTest2(){
	Boolean result = ReviewValidator.validateRating(0);
	Assert.assertFalse(result);	
	}
	
	@Test
	public void validateRatingInvalidTest3(){
	Boolean result = ReviewValidator.validateRating(6);
	Assert.assertFalse(result);	
	}
	
	@Test
	public void validateRatingvalidTest(){
	Boolean result = ReviewValidator.validateRating(2);
	Assert.assertTrue(result);	
	}
	
	@Test
	public void validatorUserIdInvalidTest() throws WanderLustException{
		ee.expect(Exception.class);
		ee.expectMessage("ReviewValidator.INVALID_USER_ID");
		ReviewValidator.validatorUserId(null);	
	}
	
	@Test
	public void validateReviewInvalidTest() throws WanderLustException{
		ee.expect(Exception.class);
		ee.expectMessage("ReviewValidator.INVALID_REVIEW");
		ReviewDTO r =new ReviewDTO();
		BookingDTO b = new BookingDTO();
		b.setBookingId(111);
		r.setRating(null);
		//r.setBooking(b);
		ReviewValidator.validateReview(r);
	}
	
	@Test
	public void validateReviewInvalidTest2() throws WanderLustException{
		ee.expect(Exception.class);
		ee.expectMessage("ReviewValidator.INVALID_REVIEW");
		ReviewDTO r =new ReviewDTO();
		BookingDTO b = new BookingDTO();
		b.setBookingId(111);
		r.setRating(6);
		//r.setBooking(b);
		ReviewValidator.validateReview(r);
	}
	
	@Test
	public void validateReviewInvalidTest3() throws WanderLustException{
		ee.expect(Exception.class);
		ee.expectMessage("ReviewValidator.INVALID_REVIEW");
		ReviewDTO r =new ReviewDTO();
		BookingDTO b = new BookingDTO();
		b.setBookingId(111);
		r.setRating(0);
		//r.setBooking(b);
		ReviewValidator.validateReview(r);
	}
	
	@Test
	public void validateReviewInvalidTest4() throws WanderLustException{
		ee.expect(Exception.class);
		ee.expectMessage("ReviewValidator.INVALID_BOOKING_ID");
		ReviewDTO r =new ReviewDTO();
			BookingDTO b = new BookingDTO();
			b.setBookingId(null);
		r.setRating(3);
		//r.setBooking(b);
		ReviewValidator.validateReview(r);
	}

}
