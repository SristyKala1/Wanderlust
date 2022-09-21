package com.infy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.infy.entity.Review;

public interface ReviewRepository extends CrudRepository<Review , Integer> {
	
	@Query("SELECT r FROM Review r where r.bookingEntity.userEntity.userId=:userId")
	public List<Review> findByReviewBookingUserUserID(@Param("userId") Integer userId);
	@Query("SELECT r from Review r")
	public List<Review> findByReview(Review review);

}
