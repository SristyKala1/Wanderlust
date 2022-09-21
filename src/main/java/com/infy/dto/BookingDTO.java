package com.infy.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingDTO {
	
	 private Integer bookingId;
	 private LocalDate checkIn;
	 private LocalDate checkOut;
	 private Integer noOfPeople;
	 private Float totalCost;
	 private LocalDateTime timeOfBooking = LocalDateTime.now();
	 private DestinationDTO destinationDTO;
	 private UserDTO user;
	 
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
	public DestinationDTO getDestination() {
		return destinationDTO;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO userDTO) {
		this.user = userDTO;
	}
	public DestinationDTO getDestinationDTO() {
		return destinationDTO;
	}
	public void setDestinationDTO(DestinationDTO destinationDTO) {
		this.destinationDTO = destinationDTO;
	}
	
}
