package com.infy.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.dto.BookingDTO;
import com.infy.dto.DestinationDTO;

import com.infy.service.BookingService;

@CrossOrigin
@RestController
@RequestMapping("BookingAPI")
public class BookingAPI {
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private Environment environment;

	//Booking API
	@PostMapping(value = "/booking/{userId}/{destinationId}")
	public ResponseEntity<Integer> addBooking(@RequestBody BookingDTO booking,@PathVariable Integer userId,@PathVariable String destinationId ) throws Exception {
		
		try {		
			Integer bookingId = bookingService.addBooking(booking,userId,destinationId);
			return new ResponseEntity<Integer>(bookingId,HttpStatus.CREATED);
		}
		catch(Exception e) {
			String message=environment.getProperty(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,message,e);
		}
	}
	
	//Destination API
	@GetMapping(value="/checkItinerary/{destinationId}")
	public ResponseEntity<DestinationDTO> getDestinationDetails(@PathVariable String destinationId) throws Exception{
		
		try{
			DestinationDTO destinationDTO = bookingService.getDestinationDetails(destinationId);
			ResponseEntity<DestinationDTO> response = new ResponseEntity <DestinationDTO>(destinationDTO, HttpStatus.OK);

			return response;
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()));
		}		
	}
	
	//ViewBooking API
	@GetMapping(value="/viewBooking/{userId}")
	public ResponseEntity<List<BookingDTO>> getViewBooking(@PathVariable Integer userId) throws Exception {
		try{
			List<BookingDTO> booking = bookingService.getViewBooking(userId);
			return new ResponseEntity <List<BookingDTO>>(booking, HttpStatus.OK);
			
		}
			catch(Exception e){
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()));
			}	
	}
	
	
	//Delete Booking API
	@DeleteMapping(value="/deleteBooking/{bookingId}")
	public ResponseEntity<Float>  getCancelBooking(@PathVariable Integer bookingId) throws Exception{
		
		try{
			Float refundAmount = bookingService.cancelBooking(bookingId);
			ResponseEntity <Float> response = new ResponseEntity <Float>(refundAmount, HttpStatus.OK);
			
			return response;
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()));
		}
		
	}

}
