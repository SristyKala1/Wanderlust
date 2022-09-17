package com.infy.entity;

import javax.persistence.*;



@Entity
@Table(name="DESTINATION")
public class Destination {
	@Id
	private String destinationId;
	private String continent;
	private String destinationName;
	private String imageUrl;
	private Integer noOfNights;
	private Float flightCharge;
	private Float chargePerPerson;
	private Float discount;
	private Float rating;
	private Float noOfRating;
	private Integer availability;
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id", unique = true)
	private Details detailsEntity;
	
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
	public Integer getAvailability() {
		return availability;
	}
	public void setAvailability(Integer availability) {
		this.availability = availability;
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
	public Details getDetailsEntity() {
		return detailsEntity;
	}
	public void setDetailsEntity(Details detailsEntity) {
		this.detailsEntity = detailsEntity;
	}

}
