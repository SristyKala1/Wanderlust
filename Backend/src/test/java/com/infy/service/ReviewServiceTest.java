
package com.infy.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.infy.dto.ReviewDTO;
import com.infy.entity.Booking;
import com.infy.entity.Review;
import com.infy.exception.WanderLustException;
import com.infy.repository.ReviewRepository;
import com.infy.service.ReviewSerivceImpl;
//import com.infy.service.ReviewSerivceImpl;


@SpringBootTest
public class ReviewServiceTest {

	@Mock
	ReviewRepository reviewRepository;

	@InjectMocks
	ReviewSerivceImpl reviewServiceImpl;
	
	
	@Rule
	@SuppressWarnings("deprecation")
	public ExpectedException ee = ExpectedException.none();
	
	@Test
	public void searchReviewInvalidTest() throws WanderLustException{
		ee.expect(WanderLustException.class);
		ee.expectMessage("ReviewService.REVIEW_NOT_DONE_BY_USER");
		List<Review> list = null;
		Mockito.when(reviewRepository.findByReviewBookingUserUserID(101)).thenReturn(list);
		reviewServiceImpl.searchReview(101);
	}
	
	
	@Test
	public void searchReviewValidTest() throws WanderLustException{
		List<Review> expect = new ArrayList<Review>() ;
		Review r =new Review();
		Booking b = new Booking();
		b.setBookingId(111);
		r.setRating(6);
		r.setBookingEntity(b);
		expect.add(r);
		Mockito.when(reviewRepository.findByReviewBookingUserUserID(1001)).thenReturn(expect);
		List<ReviewDTO> actual = reviewServiceImpl.searchReview(1001);
		Assertions.assertNotNull(actual);
	}
	
	@Test
	public void viewReviewInvalidTest() throws WanderLustException{
		ee.expect(WanderLustException.class);
		ee.expectMessage("ReviewService.NOT_DONE_ANY_RATING");
		Review review=new Review();
		List<Review> list = null;
		Mockito.when(reviewRepository.findByReview(review)).thenReturn(list);
		reviewServiceImpl.viewReview();
	}
	
	@Test
	public void viewReviewValidTest() throws WanderLustException{
		List<Review> expect = new ArrayList<Review>() ;
		Review r =new Review();
		Booking b = new Booking();
		b.setBookingId(111);
		r.setRating(6);
		r.setBookingEntity(b);
		expect.add(r);
		Mockito.when(reviewRepository.findByReview(r)).thenReturn(expect);
		List<ReviewDTO> actual = reviewServiceImpl.viewReview();
		Assertions.assertNotNull(actual);
	}
	
	
	@Test
	public void addReviewInvalidTest() throws WanderLustException{
		ee.expect(WanderLustException.class);
		ee.expectMessage("ReviewService.RATING_FAILED");
		ReviewDTO r =new ReviewDTO();
		Booking b = new Booking();
		b.setBookingId(111);
		r.setRating(2);
		//r.setBooking(b);
		r.setReviewId(null);
		List<Review> expect = new ArrayList<Review>() ;
		Mockito.when(reviewRepository.findByReviewBookingUserUserID(1001)).thenReturn(expect);
		reviewServiceImpl.addReview(r);
	}
	
	@Test
	public void addReviewValidTest() throws WanderLustException{
		List<Review> expect = new ArrayList<Review>() ;
		ReviewDTO r =new ReviewDTO();
		Booking b = new Booking();
		b.setBookingId(111);
		r.setRating(2);
		//r.setBooking(b);
		r.setReviewId(10001);
		Mockito.when(reviewRepository.findByReviewBookingUserUserID(101)).thenReturn(expect);
		ReviewDTO actual = reviewServiceImpl.addReview(r);
		Assertions.assertEquals(r.getReviewId(), actual.getReviewId());;
	}
}


