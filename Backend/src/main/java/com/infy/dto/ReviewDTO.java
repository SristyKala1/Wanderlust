package com.infy.dto;

import java.time.LocalDateTime;

public class ReviewDTO {

	private Integer reviewId;
	private String review;
	private Integer rating;
	private LocalDateTime reviewDate;
	private String url;
	private BookingDTO booking;
	
	public BookingDTO getBooking() {
		return booking;
	}
	public void setBooking(BookingDTO bookingDTO) {
		this.booking = bookingDTO;
	}
	
	public Integer getReviewId() {
		return reviewId;
	}
	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public LocalDateTime getReview_Date() {
		return reviewDate;
	}
	public void setReview_Date(LocalDateTime review_Date) {
		this.reviewDate = review_Date;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
