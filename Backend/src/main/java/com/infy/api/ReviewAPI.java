package com.infy.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.dto.ReviewDTO;
import com.infy.exception.WanderLustException;
import com.infy.service.ReviewService;

@CrossOrigin
@RestController
@RequestMapping("ReviewAPI")
public class ReviewAPI {
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private Environment environment;
	
	// Search review API
	@GetMapping(value="/searchReview/{userId}")
	public ResponseEntity<List<ReviewDTO>> getSearchReview(@PathVariable Integer userId) throws WanderLustException{
		try{
			
			List<ReviewDTO> reviewList = reviewService.searchReview(userId);
			ResponseEntity <List<ReviewDTO>> response = new ResponseEntity <List<ReviewDTO>>(reviewList, HttpStatus.OK);
			return response;
		}
			catch(Exception e){
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()));
			}	
	}

	
	//View Review API
		@GetMapping(value="/viewReview")
		public ResponseEntity<List<ReviewDTO>> getViewReview () throws WanderLustException{
			try{
				List<ReviewDTO> reviewList = reviewService.viewReview();
				ResponseEntity <List<ReviewDTO>> response = new ResponseEntity <List<ReviewDTO>>(reviewList, HttpStatus.OK);
				return response;
			}
				catch(Exception e){
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()));
				}	
			
		}
		
		
		//Add Review API
		@PostMapping(value="/addReview")
		public ResponseEntity<ReviewDTO> getBookPackage(@RequestBody ReviewDTO reviewDTO) throws WanderLustException {
			try {
				System.out.println("api");
				ReviewDTO r = reviewService.addReview(reviewDTO);
				ResponseEntity<ReviewDTO> response=new ResponseEntity<ReviewDTO>(r,HttpStatus.CREATED);
				return response;
			}
			catch(Exception e) {
				String message=environment.getProperty(e.getMessage());
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,message,e);
				
			}
		}
		
}
