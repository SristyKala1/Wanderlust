package com.infy.service;

import java.util.List;

import com.infy.dto.ReviewDTO;
import com.infy.exception.WanderLustException;

public interface ReviewService {
	
	public List<ReviewDTO> searchReview(Integer reviewId) throws WanderLustException;
	public List<ReviewDTO> viewReview() throws WanderLustException;
	public ReviewDTO addReview(ReviewDTO reviewDTO) throws WanderLustException;


}
