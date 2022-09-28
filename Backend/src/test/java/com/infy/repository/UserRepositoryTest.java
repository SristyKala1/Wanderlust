//package com.infy.repository.test;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import com.infy.entity.User;
//import com.infy.repository.UserRepository;
//
//@DataJpaTest
//public class UserRepositoryTest {
//
//	@Autowired
//	UserRepository userRepository;
//
//	private User user;
//
//	@BeforeEach
//	public void setUp() {
//		user = new User();
//		user.setEmailId("fahad@infosys.com");
//		user.setPassword("Fahad@123");
//		user.setUserId(1234);
//		user.setUserName("Fahad rahman");
//		user.setContactNumber("9988776655");
//	}
//
//	// invalid login
//	@Test
//	public void invalidLogin() throws Exception {
//		Assertions.assertNull(userRepository.findByContactNumber("1234567890"));
//	}
//
//	// valid register
//	@Test
//	public void validRegister() throws Exception {
//
//		userRepository.save(user);
//		Assertions.assertNotNull(userRepository.findByContactNumber("9988776655"));
//	}
//}
