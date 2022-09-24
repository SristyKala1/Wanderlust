package com.infy.service;

import java.security.NoSuchAlgorithmException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.dto.UserDTO;
import com.infy.entity.User;
import com.infy.exception.WanderLustException;
import com.infy.repository.UserRepository;
import com.infy.utility.HashingUtility;
import com.infy.validator.UserValidator;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDTO authenticateUser(String contactNumber, String password) throws WanderLustException {

		User optionalUser = userRepository.findByContactNumber(contactNumber);
		if (optionalUser == null) {
			throw new WanderLustException("UserService.INVALID_CREDENTIALS");
		}

		String passwordFromDB = optionalUser.getPassword();

		if (passwordFromDB != null) {
			try {
				String hashedPassword = HashingUtility.getHashValue(password);
				if (hashedPassword.equals(passwordFromDB)) {
					UserDTO userObject = new UserDTO();
					userObject.setContactNumber(optionalUser.getContactNumber());
					userObject.setEmailId(optionalUser.getEmailId());
					userObject.setUserId(optionalUser.getUserId());
					userObject.setUserName(optionalUser.getUserName());
					return userObject;
				} else
					throw new WanderLustException("UserService.INVALID_CREDENTIALS");
			} catch (NoSuchAlgorithmException e) {
				throw new WanderLustException("UserService.HASH_FUNCTION_EXCEPTION");
			}

		} else
			throw new WanderLustException("UserService.INVALID_CREDENTIALS");

	}

	@Override
	public String registerUser(UserDTO user) throws WanderLustException {
		UserValidator.validateUserForRegister(user);
		if (userRepository.findByContactNumber(user.getContactNumber()) != null)
			throw new WanderLustException("UserService.CONTACT_NUMBER_ALREADY_EXISTS");
		try {
			String hashedPassword = HashingUtility.getHashValue(user.getPassword());
			User userEntity = new User();
			userEntity.setContactNumber(user.getContactNumber());
			userEntity.setEmailId(user.getEmailId());
			userEntity.setPassword(hashedPassword);
			userEntity.setUserId(user.getUserId());
			userEntity.setUserName(user.getUserName());
			userRepository.save(userEntity);
			return userEntity.getUserName();
		} catch (NoSuchAlgorithmException e) {
			throw new WanderLustException("UserService.HASH_FUNCTION_EXCEPTION");
		}

	}

}
