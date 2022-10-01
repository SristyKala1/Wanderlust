package com.infy.validator.test;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.boot.test.context.SpringBootTest;

import com.infy.dto.UserDTO;
import com.infy.validator.UserValidator;

@SpringBootTest
public class UserValidatorTest {

	@Rule
	public ExpectedException ee = ExpectedException.none();

	@Test
	public void validatePasswordValidTest() {
		Boolean result = UserValidator.validatePassword("Scott@123");
		Assert.assertTrue(result);
	}

	@Test
	public void validatePasswordInvalidTest() {
		Boolean result = UserValidator.validatePassword("scott@123");
		Assert.assertFalse(result);
	}

	@Test
	public void validatePasswordInValidTest1() throws Exception {
		Boolean result = UserValidator.validatePassword("A123456");
		Assert.assertFalse(result);
	}

	@Test
	public void validatePasswordInValidTest2() throws Exception {
		Boolean result = UserValidator.validatePassword("a123456");
		Assert.assertFalse(result);
	}

	@Test
	public void validatePasswordInValidTest3() throws Exception {
		Boolean result = UserValidator.validatePassword("null");
		Assert.assertFalse(result);
	}

	@Test
	public void validatePasswordInValidTest4() throws Exception {
		Boolean result = UserValidator.validatePassword("45658963214785");
		Assert.assertFalse(result);
	}

	@Test
	public void validateContactNumberValidTest() {
		Boolean result = UserValidator.validateContactNumber("8420305506");
		Assert.assertTrue(result);
	}

	@Test
	public void validateContactNumberInvalidTest() {
		Boolean result = UserValidator.validateContactNumber("5678901232");
		Assert.assertFalse(result);
	}

	@Test
	public void validateContactNumberInvalidTest1() {
		Boolean result = UserValidator.validateContactNumber("98678901");
		Assert.assertFalse(result);
	}

	@Test
	public void validateContactNumberInvalidTest2() {
		Boolean result = UserValidator.validateContactNumber(null);
		Assert.assertFalse(result);
	}

	@Test
	public void validateContactNumberInvalidTest3() {
		Boolean result = UserValidator.validateContactNumber("56789ab232");
		Assert.assertFalse(result);
	}

	@Test
	public void validateUserNameValidTest() {
		Boolean result = UserValidator.validateUserName("Brett Lee");
		Assert.assertTrue(result);
	}

	@Test
	public void validateUserNameInvalidTest() {
		Boolean result = UserValidator.validateUserName(" Brett Lee");
		Assert.assertFalse(result);
	}

	@Test
	public void validateUserNameInvalidTest1() {
		Boolean result = UserValidator.validateUserName(null);
		Assert.assertFalse(result);
	}

	@Test
	public void validateUserNameInvalidTest2() {
		Boolean result = UserValidator.validateUserName("A123ndy");
		Assert.assertFalse(result);
	}

	@Test
	public void validateUserNameInvalidTest3() {
		Boolean result = UserValidator.validateUserName("@ndy");
		Assert.assertFalse(result);
	}

	@Test
	public void validateEmailIdValidTest() throws Exception {
		Boolean result = UserValidator.validateEmailId("Jack@infosys.com");
		Assert.assertTrue(result);
	}

	@Test
	public void validateEmailIdInvalidTest() throws Exception {
		Boolean result = UserValidator.validateEmailId("Tom12@infosys1.c2om");
		Assert.assertFalse(result);
	}

	@Test
	public void validateUserLoginInvalidContactNumber() throws Exception {
		ee.expect(Exception.class);
		ee.expectMessage("UserValidator.INVALID_CONTACT_NUMBER_FORMAT");
		UserValidator.validateUserForLogin("98678901", "Jack@123");
	}

	@Test
	public void validateUserLoginInvalidPassword() throws Exception {
		ee.expect(Exception.class);
		ee.expectMessage("UserValidator.INVALID_PASSWORD_FORMAT");
		UserValidator.validateUserForLogin("8420305506", "jack123");
	}

	@Test
	public void validateUserForRegisterInvalidContactNumber() throws Exception {
		ee.expect(Exception.class);
		ee.expectMessage("UserValidator.INVALID_CONTACT_NUMBER_FORMAT");
		UserDTO user = new UserDTO();
		user.setContactNumber("84203055");
		user.setPassword("Jack@123");
		user.setUserName("Jack");
		user.setEmailId("Jack@infosys.com");
		UserValidator.validateUserForRegister(user);
	}

	@Test
	public void validateUserForRegisterInvalidPassword() throws Exception {
		ee.expect(Exception.class);
		ee.expectMessage("UserValidator.INVALID_PASSWORD_FORMAT");
		UserDTO user = new UserDTO();
		user.setContactNumber("8420305506");
		user.setPassword("jack123");
		user.setUserName("Jack");
		user.setEmailId("Jack@infosys.com");
		UserValidator.validateUserForRegister(user);
	}

	@Test
	public void validateUserForRegisterInvalidUserName() throws Exception {
		ee.expect(Exception.class);
		ee.expectMessage("UserValidator.INVALID_USERNAME_FORMAT");
		UserDTO user = new UserDTO();
		user.setContactNumber("8420305506");
		user.setPassword("Jack@123");
		user.setUserName(" Jack Ryan");
		user.setEmailId("Jack@infosys.com");
		UserValidator.validateUserForRegister(user);
	}

	@Test
	public void validateUserForRegisterInvalidEmailId() throws Exception {
		ee.expect(Exception.class);
		ee.expectMessage("UserValidator.INVALID_EMAIL_ID_FORMAT");
		UserDTO user = new UserDTO();
		user.setContactNumber("8420305506");
		user.setPassword("Jack@123");
		user.setUserName("Jack Ryan");
		user.setEmailId("Jack2@infosys.com");
		UserValidator.validateUserForRegister(user);
	}

}
