package com.infy.service.test;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.infy.dto.DestinationDTO;
import com.infy.entity.Destination;
import com.infy.exception.WanderLustException;
import com.infy.repository.DestinationRepository;
import com.infy.service.DestinationServiceImpl;

@SpringBootTest
public class DestinationServiceTest {

	@Mock
	DestinationRepository destination;
	
	@InjectMocks
	DestinationServiceImpl destinationServiceImpl;

	
	@Test
	public void invalidecontinentSearch() throws Exception{

		List<Destination> destinationons = null;
//		Mockito.when(destination.findByContinentIgnoreCase("africa")).thenReturn(destinationons);
//		destinationServiceImpl.getDestination("africa");
//		WanderLustException exception = Assertions.assertThrows(WanderLustException.class,
//				() -> destinationServiceImpl.getDestination("Europe"));
//		Assertions.assertEquals("DestinationService.HAVING_NO_DESTINATIONS", exception.getMessage());
	}
	
	@Test
	public void validecontinentSearch() throws Exception{
		List<Destination> expected = new LinkedList<Destination>();
		Destination d = new Destination();
		d.setDestinationId("D1001");
		d.setContinent("Europe");
		d.setDestinationName("A Week in Greece: Athens, Mykonos & Santorini");
		d.setImageUrl("/assets/geece.jpg");
		d.setNoOfNights(7);
		d.setFlightCharge(500.0f);
		d.setChargePerPerson(2499.0f);
		d.setDiscount(0.0f);
		d.setAvailability(30);
		d.setRating(4f);
		d.setNoOfNights(1);
	//	d.setDetailsEntity(DL101);
		expected.add(d);
//		Mockito.when(destination.findByContinentIgnoreCase("Europe")).thenReturn(expected);
//		List<DestinationDTO> actual = destinationServiceImpl.getDestination("Europe");
//		WanderLustException exception = Assertions.assertThrows(WanderLustException.class,
//				() -> destinationServiceImpl.getDestination("Europe"));
//		Assertions.assertEquals("DestinationService.HAVING_NO_DESTINATIONS", exception.getMessage());
	}
	
	@Test
	public void invalideHotDeals() throws Exception{
		List<Destination> destinations = null;
//		Mockito.when(destination.findByDiscountGreaterThan(0.0F)).thenReturn(destinations);
//		WanderLustException exception = Assertions.assertThrows(WanderLustException.class,
//				() -> 		destinationServiceImpl.getHotDeals());
//		Assertions.assertNotEquals("DestinationService.NO_HOTDEALS", exception.getMessage());
	}
	
	@Test
	public void valideHotDeals() throws Exception{
		List<Destination> expected = new LinkedList<Destination>();
		Destination d = new Destination();
		d.setDestinationId("D1001");
		expected.add(d);
//		Mockito.when(destination.findByDiscountGreaterThan(0.0f)).thenReturn(expected);
//		List<DestinationDTO> actual = destinationServiceImpl.getHotDeals();
//		WanderLustException exception = Assertions.assertThrows(WanderLustException.class,
//				() -> 		destinationServiceImpl.getHotDeals());
//		Assertions.assertEquals("DestinationService.NO_HOTDEALS", exception.getMessage());
	}
}
