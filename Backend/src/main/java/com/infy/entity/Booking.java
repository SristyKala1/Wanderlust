package com.infy.entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name="BOOKING")
public class Booking{
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer bookingId;
	 private LocalDate checkIn;
	 private LocalDate checkOut;
	 private Integer noOfPeople;
	 private Float totalCost;
	 private LocalDateTime timeOfBooking = LocalDateTime.now();
	 @OneToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "destination_id", unique = true)
	 private Destination destinationEntity;
	 @ManyToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name = "user_id")
	 private User userEntity;
	 
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public LocalDate getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}
	public LocalDate getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}
	public Integer getNoOfPeople() {
		return noOfPeople;
	}
	public void setNoOfPeople(Integer noOfPeople) {
		this.noOfPeople = noOfPeople;
	}
	public Float getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Float totalCost) {
		this.totalCost = totalCost;
	}
	public LocalDateTime getTimeOfBooking() {
		return timeOfBooking;
	}
	public void setTimeOfBooking(LocalDateTime timeOfBooking) {
		this.timeOfBooking = timeOfBooking;
	}
	public Destination getDestinationEntity() {
		return destinationEntity;
	}
	public void setDestinationEntity(Destination destinationEntity) {
		this.destinationEntity = destinationEntity;
	}
	public User getUserEntity() {
		return userEntity;
	}
	public void setUserEntity(User userEntity) {
		this.userEntity = userEntity;
	}
	
}