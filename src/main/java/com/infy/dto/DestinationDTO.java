package com.infy.dto;

import javax.validation.constraints.NotNull;


import com.infy.entity.Details;


public class DestinationDTO {

	
	private String destinationId;
	@NotNull(message = "{invalid.continent}")
	private String continent;
	@NotNull(message = "{invalid.destinationName}")
	private String destinationName;
	@NotNull(message = "{invalid.imageurl}")
	private String imageUrl;
	@NotNull(message = "{invalid.noOfNight}")
	private Integer noOfNights;
	@NotNull(message = "{invalid.flightCharge}")
	private Float flightCharge;
	@NotNull(message = "{invalid.chargePerPerson}")
	private Float chargePerPerson;
	@NotNull(message = "{invalid.discount}")
	private Float discount;
	@NotNull(message = "{invalid.rating}")
	private Float rating;
//	@NotNull(message = "{invalid.noOfRating}")
	private Float noOfRating;
	@NotNull(message = "{invalid.availability}")
	private Integer availability;

	//private Details detailsEntity;
	private DetailsDTO detailsDTO;
	
//	public Details getDetailsEntity() {
//		return detailsEntity;
//	}
//	public void setDetailsEntity(Details detailsEntity) {
//		this.detailsEntity = detailsEntity;
//	}
	public String getDestinationId() {
		return destinationId;
	}
	public void setDestinationId(String destinationId) {
		this.destinationId = destinationId;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public String getDestinationName() {
		return destinationName;
	}
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Integer getNoOfNights() {
		return noOfNights;
	}
	public void setNoOfNights(Integer noOfNights) {
		this.noOfNights = noOfNights;
	}
	public Float getFlightCharge() {
		return flightCharge;
	}
	public void setFlightCharge(Float flightCharge) {
		this.flightCharge = flightCharge;
	}
	public Float getChargePerPerson() {
		return chargePerPerson;
	}
	public void setChargePerPerson(Float chargePerPerson) {
		this.chargePerPerson = chargePerPerson;
	}
	public Float getDiscount() {
		return discount;
	}
	public void setDiscount(Float discount) {
		this.discount = discount;
	}
	public Float getRating() {
		return rating;
	}
	public void setRating(Float rating) {
		this.rating = rating;
	}
	public Float getNoOfRating() {
		return noOfRating;
	}
	public void setNoOfRating(Float noOfRating) {
		this.noOfRating = noOfRating;
	}
	public Integer getAvailability() {
		return availability;
	}
	public void setAvailability(Integer availability) {
		this.availability = availability;
	}
	
	public DetailsDTO getDetailsDTO() {
		return detailsDTO;
	}
	public void setDetailsDTO(DetailsDTO detailsDTO) {
		this.detailsDTO = detailsDTO;
	}
	
}
