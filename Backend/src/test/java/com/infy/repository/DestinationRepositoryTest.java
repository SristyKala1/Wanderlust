//package com.infy.repository.test;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.infy.repository.DestinationRepository;
//
//
//@SpringBootTest
//@Transactional
//@Rollback(true)
//public class DestinationRepositoryTest {
//	
//	@Autowired
//	DestinationRepository destination;
//	
//	@Test
//	public void invalideContinentSearch() throws Exception{
//		Assert.assertNotNull(destination.findByContinentIgnoreCase("Africa"));
//	}
//	
//	@Test
//	public void valideContinentSearch() throws Exception{
//		Assert.assertNotNull(destination.findByContinentIgnoreCase("Europe"));
//	}
//	
//	@Test
//	public void valideContinentSearch2() throws Exception{
//		Assert.assertNotNull(destination.findByContinentIgnoreCase("EUROPE"));
//	}
//	
//	@Test
//	public void valideContinentSearch3() throws Exception{
//		Assert.assertNotNull(destination.findByContinentIgnoreCase("europe"));
//	}
//
//	@Test
//	public void valideHotDeals() throws Exception{
//		Assert.assertNotNull(destination.findByDiscountGreaterThan(0.0F));
//	}
//}
