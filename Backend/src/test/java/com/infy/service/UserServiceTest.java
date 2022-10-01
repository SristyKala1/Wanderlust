package com.infy.service.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.infy.dto.UserDTO;
import com.infy.entity.User;
import com.infy.exception.WanderLustException;
import com.infy.repository.UserRepository;
import com.infy.service.UserServiceImpl;

@SpringBootTest
public class UserServiceTest {

	@Mock
	UserRepository userRepository;

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Test
	public void invalidLoginInvalidUser() throws Exception {
		User user = new User();

		String contactNo = "1234567890";
		String password = "abcd";

		user.setPassword("xyz");

		Mockito.when(userRepository.findByContactNumber(contactNo)).thenReturn(user);
		WanderLustException exception = Assertions.assertThrows(WanderLustException.class,
				() -> userServiceImpl.authenticateUser(contactNo, password));
		Assertions.assertEquals("UserService.INVALID_CREDENTIALS", exception.getMessage());

	}

	@Test
	public void invalidLoginNullPassword() throws Exception {
		User user = new User();

		String contactNo = "1234567890";
		String password = "abcd";

		user.setPassword(null);

		Mockito.when(userRepository.findByContactNumber(contactNo)).thenReturn(user);
		WanderLustException exception = Assertions.assertThrows(WanderLustException.class,
				() -> userServiceImpl.authenticateUser(contactNo, password));
		Assertions.assertEquals("UserService.INVALID_CREDENTIALS", exception.getMessage());

	}

//	@Test
//	public void invalidLoginInvalidPassword() throws Exception {
//		ee.expect(Exception.class);
//		ee.expectMessage("UserService.INVALID_CREDENTIALS");
//		UserDTO users = new UserDTO();
//		users.setPassword("abc");
//		when(userRepository.getUserByContactNumber(anyString())).thenReturn(users);
//		userServiceImpl.authenticateUser("8889765465", "Scott@123");
//	}

//	@Test
//	public void validLogin() throws Exception {
//		UserDTO expected = new UserDTO();
//		expected.setPassword("3284cbd43ac6fc733d7c3d2176e7a52bbaeba81471702ec332a0a65689cf16e3");
//		expected.setContactNumber("8884967823");
//		when(userRepository.getUserByContactNumber(anyString())).thenReturn(expected);
//		UserDTO actual = userServiceImpl.authenticateUser("8889765465", "Scott@123");
//		Assertions.assertEquals(expected.getContactNumber(), actual.getContactNumber());
//	}
//
//	@Test
//	public void invalidRegister() throws Exception {
//		ee.expect(Exception.class);
//		ee.expectMessage("UserService.CONTACT_NUMBER_ALREADY_EXISTS");
//		UserDTO expected = new UserDTO();
//		expected.setContactNumber("8884967823");
//		when(userRepository.getUserByContactNumber(anyString())).thenReturn(expected);
//		UserDTO actual = new UserDTO();
//		actual.setContactNumber("8884967823");
//		actual.setPassword("Scott@123");
//		actual.setUserName("Scott");
//		actual.setEmailId("scott@stark.com");
//		userServiceImpl.registerUser(actual);
//	}
//
//	@Test
//	public void validRegister() throws Exception {
//		UserDTO actual = new UserDTO();
//		actual.setContactNumber("8884563253");
//		actual.setPassword("Scott@123");
//		actual.setUserName("Scott");
//		actual.setEmailId("scott@stark.com");
//		String expected = "Scott";
//		when(userRepository.registerUser(any())).thenReturn(expected);
//		Assertions.assertEquals(expected, userServiceImpl.registerUser(actual));
//	}

}
