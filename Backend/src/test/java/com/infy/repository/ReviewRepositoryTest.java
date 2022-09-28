//package com.infy.repository.test;
//
//import java.time.LocalDateTime;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import com.infy.entity.Booking;
//import com.infy.entity.Review;
//import com.infy.exception.WanderLustException;
//import com.infy.repository.ReviewRepository;
//
//@DataJpaTest
//public class ReviewRepositoryTest {
//	
//	@Autowired
//	ReviewRepository reviewRepository;
//	
//	private Review review;
//	
//	@BeforeEach
//	public void setUp() {
//		review = new Review();
//		review.setReviewId(10001);
//		review.setRating(5);
//		review.setReview("Very Good");
//		review.setReview_Date(LocalDateTime.now());
//		review.setUrl("");
//		
//		Booking booking=new Booking();
//		booking.setBookingId(1001);
//		review.setBookingEntity(booking);
//	}
//	
//	@Test
//	public void invalidSearchReview() throws WanderLustException{
//		Assertions.assertTrue(reviewRepository.findByReviewBookingUserUserID(10001).isEmpty());
//	}
//	
//	
//	@Test
//	public void validSearchReview() throws WanderLustException{
//		Assertions.assertNotNull(reviewRepository.findByReviewBookingUserUserID(101));
//	}
//	
//
//	
//	@Test
//	public void validviewReview() throws WanderLustException{
//		Assertions.assertNotNull(reviewRepository.findByReview(review));
//	}
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
