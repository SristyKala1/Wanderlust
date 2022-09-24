package com.infy.validator;

import com.infy.dto.UserDTO;
import com.infy.exception.WanderLustException;

public class UserValidator {

	public static void validateUserForLogin(String contactNumber, String password) throws WanderLustException {

		if (!validateContactNumber(contactNumber))
			throw new WanderLustException("UserValidator.INVALID_CONTACT_NUMBER_FORMAT");

		if (!validatePassword(password))
			throw new WanderLustException("UserValidator.INVALID_PASSWORD_FORMAT");
	}

	public static void validateUserForRegister(UserDTO user) throws WanderLustException {

		if (!validateContactNumber(user.getContactNumber()))
			throw new WanderLustException("UserValidator.INVALID_CONTACT_NUMBER_FORMAT");

		if (!validatePassword(user.getPassword()))
			throw new WanderLustException("UserValidator.INVALID_PASSWORD_FORMAT");

		if (!validateUserName(user.getUserName()))
			throw new WanderLustException("UserValidator.INVALID_USERNAME_FORMAT");

		if (!validateEmailId(user.getEmailId()))
			throw new WanderLustException("UserValidator.INVALID_EMAIL_ID_FORMAT");
	}

	public static Boolean validatePassword(String password) {
		if (password == null)
			return false;
		Boolean flag = false;
		if (password.length() >= 7 && password.length() <= 20)
			if (password.matches(".*[A-Z]+.*"))
				if (password.matches(".*[a-z]+.*"))
					if (password.matches(".*[0-9]+.*"))
						if (password.matches(".*[!@#$%^&*].*"))
							flag = true;
		return flag;
	}

	public static Boolean validateContactNumber(String contactNumber) {
		if (contactNumber == null)
			return false;
		Boolean flag = false;
		if (contactNumber.matches("[6-9][0-9]{9}"))
			flag = true;
		return flag;
	}

	public static Boolean validateUserName(String name) {
		if (name == null || name.trim().length() != name.length())
			return false;
		return name.matches("[A-Za-z ]+");
	}

	public static Boolean validateEmailId(String emailId) {
		if (emailId == null)
			return false;
		return emailId.matches("[A-Za-z]+@[A-Za-z]+[.]com");
	}

}
