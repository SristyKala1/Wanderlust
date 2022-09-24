package com.infy.service;

import com.infy.dto.UserDTO;
import com.infy.exception.WanderLustException;

public interface UserService {

	public UserDTO authenticateUser(String contactNumber, String password) throws WanderLustException;

	public String registerUser(UserDTO user) throws WanderLustException;
}
