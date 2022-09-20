package com.infy.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="REVIEW")
public class Review {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reviewId;
	private String review;
	private Integer rating;
	private LocalDateTime reviewDate =  LocalDateTime.now();
	private String url;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id")
	private Booking bookingEntity;
	 
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
	public Booking getBookingEntity() {
		return bookingEntity;
	}
	public void setBookingEntity(Booking bookingEntity) {
		this.bookingEntity = bookingEntity;
	}
}