package com.infy.validator.test;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.boot.test.context.SpringBootTest;

import com.infy.validator.DestinationSearchValidator;


@SpringBootTest
public class SearchValidatorTest  {
	
	@Rule
	public ExpectedException ee = ExpectedException.none();
	
	@Test
	public void validateContinentValidTest(){
		Boolean result = DestinationSearchValidator.validateContinent("Europe");
		Assert.assertTrue(result);
	}
	
	@Test
	public void validateContinentValidTest2(){
		Boolean result = DestinationSearchValidator.validateContinent("europe");
		Assert.assertTrue(result);
	}
	
	@Test
	public void validateContinentInvalidTest() {
		Boolean result = DestinationSearchValidator.validateContinent("India");
		Assert.assertFalse(result);	
	}
	
	@Test
	public void validateContinentInvalidTest2() {
		Boolean result = DestinationSearchValidator.validateContinent(null);
		Assert.assertFalse(result);	
	}
	
	@Test
	public void validateForSearch() throws Exception{
		ee.expect(Exception.class);
		ee.expectMessage("SearchValidator.INVALID_CONTINEINT");
		DestinationSearchValidator.validateSearch("India");
	}
	
}
