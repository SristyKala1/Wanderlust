package com.infy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.infy.entity.Booking;


public interface BookingRepository extends CrudRepository<Booking, Integer> {

	@Query("SELECT b FROM Booking b WHERE b.userEntity.userId=:userId")
	public List<Booking> findByBookUserId(@Param("userId") Integer userId);

}
