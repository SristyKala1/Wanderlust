package com.infy.validator;

public class DestinationSearchValidator {
	
	public static void validateSearch(String continent) throws Exception {
		if(!validateContinent(continent))
			throw new Exception("DestinationSearchValidator.INVALID_CONTINEINT");
	}

	public static boolean validateContinent(String continent) {
		if(continent == null)
			return false;
		String [] continents ={"Africa","Asia","Australia","North America","Europe","South America","Antarctica"};
		
		for(int i=0;i<continents.length;i++){
			if(continents[i].toLowerCase().equals(continent.toLowerCase())){
				return true;
			}
		}
		return false;
		
	}
}
