package com.infy.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	public User findByContactNumber(String contactNumber);

}
