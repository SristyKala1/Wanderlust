package com.infy.service;

			import java.util.ArrayList;
			import java.util.List;
			import java.util.Optional;

			import javax.transaction.Transactional;

			import org.springframework.beans.factory.annotation.Autowired;
			import org.springframework.stereotype.Service;

			import com.infy.dto.BookingDTO;
			import com.infy.dto.DestinationDTO;
			import com.infy.dto.DetailsDTO;
			import com.infy.dto.ItineraryDTO;
			import com.infy.dto.ReviewDTO;
			import com.infy.dto.UserDTO;
			import com.infy.entity.Booking;
			import com.infy.entity.Destination;
			import com.infy.entity.Details;
			import com.infy.entity.Itinerary;
			import com.infy.entity.Review;
			import com.infy.exception.WanderLustException;
			import com.infy.repository.BookingRepository;
			import com.infy.repository.DestinationRepository;
			import com.infy.repository.ReviewRepository;


			@Service(value="reviewService")
			@Transactional
			public class ReviewSerivceImpl implements ReviewService{
				
				@Autowired
				private ReviewRepository reviewRepository;
				
				@Autowired
				private BookingRepository bookingRepository;
				
				@Autowired
				private DestinationRepository destinationRepository;
				
				@Override
				public List<ReviewDTO> searchReview(Integer userId) throws WanderLustException
				{
					ReviewDTO reviewDTO=new ReviewDTO();
					
					List<Review> review=reviewRepository.findByReviewBookingUserUserID(userId);
					List<ReviewDTO> reviewList = new ArrayList<ReviewDTO>();
					for(Review reviewEntity : review) {
						reviewDTO.setReviewId(reviewEntity.getReviewId());
						reviewDTO.setReview(reviewEntity.getReview());
						reviewDTO.setRating(reviewEntity.getRating());
						reviewDTO.setUrl(reviewEntity.getUrl());
						reviewDTO.setReview_Date(reviewEntity.getReview_Date());
						
						BookingDTO bookingDTO = new BookingDTO();
						bookingDTO.setBookingId(reviewEntity.getBookingEntity().getBookingId());
						bookingDTO.setCheckIn(reviewEntity.getBookingEntity().getCheckIn());
						bookingDTO.setCheckOut(reviewEntity.getBookingEntity().getCheckOut());
						bookingDTO.setNoOfPeople(reviewEntity.getBookingEntity().getNoOfPeople());
						bookingDTO.setTimeOfBooking(reviewEntity.getBookingEntity().getTimeOfBooking());
						bookingDTO.setTotalCost(reviewEntity.getBookingEntity().getTotalCost());
						reviewDTO.setBooking(bookingDTO);
								
						DestinationDTO destinationDTO = new DestinationDTO();
						destinationDTO.setDestinationId(reviewEntity.getBookingEntity().getDestinationEntity().getDestinationId());
						destinationDTO.setAvailability(reviewEntity.getBookingEntity().getDestinationEntity().getAvailability());
						destinationDTO.setChargePerPerson(reviewEntity.getBookingEntity().getDestinationEntity().getChargePerPerson());
						destinationDTO.setContinent(reviewEntity.getBookingEntity().getDestinationEntity().getContinent());
						destinationDTO.setDestinationName(reviewEntity.getBookingEntity().getDestinationEntity().getDestinationName());
						destinationDTO.setDiscount(reviewEntity.getBookingEntity().getDestinationEntity().getDiscount());
						destinationDTO.setFlightCharge(reviewEntity.getBookingEntity().getDestinationEntity().getFlightCharge());
						destinationDTO.setImageUrl(reviewEntity.getBookingEntity().getDestinationEntity().getImageUrl());
						destinationDTO.setNoOfNights(reviewEntity.getBookingEntity().getDestinationEntity().getNoOfNights());
						destinationDTO.setRating(reviewEntity.getBookingEntity().getDestinationEntity().getRating());
						destinationDTO.setNoOfRating(reviewEntity.getBookingEntity().getDestinationEntity().getNoOfRating());
						bookingDTO.setDestinationDTO(destinationDTO);
							

						DetailsDTO detailsDTO=new DetailsDTO();
						detailsDTO.setAbout(reviewEntity.getBookingEntity().getDestinationEntity().getDetailsEntity().getAbout());
						detailsDTO.setDetailsId(reviewEntity.getBookingEntity().getDestinationEntity().getDetailsEntity().getDetailsId());
						detailsDTO.setHighlights(reviewEntity.getBookingEntity().getDestinationEntity().getDetailsEntity().getHighlights());
						detailsDTO.setPace(reviewEntity.getBookingEntity().getDestinationEntity().getDetailsEntity().getPace());
						detailsDTO.setPackageInclusion(reviewEntity.getBookingEntity().getDestinationEntity().getDetailsEntity().getPackageInclusion());
						destinationDTO.setDetailsDTO(detailsDTO);
						
						ItineraryDTO itineraryDTO=new ItineraryDTO();
						itineraryDTO.setFirstDay(reviewEntity.getBookingEntity().getDestinationEntity().getDetailsEntity().getItineraryEntity().getFirstDay());
						itineraryDTO.setItineraryId(reviewEntity.getBookingEntity().getDestinationEntity().getDetailsEntity().getItineraryEntity().getItineraryId());
						itineraryDTO.setLastDay(reviewEntity.getBookingEntity().getDestinationEntity().getDetailsEntity().getItineraryEntity().getLastDay());
						itineraryDTO.setRestOfDays(reviewEntity.getBookingEntity().getDestinationEntity().getDetailsEntity().getItineraryEntity().getRestOfDays());
						detailsDTO.setItinerary(itineraryDTO);
						
						UserDTO userDTO = new UserDTO();
						userDTO.setUserId(reviewEntity.getBookingEntity().getUserEntity().getUserId());
						userDTO.setUserName(reviewEntity.getBookingEntity().getUserEntity().getUserName());
						userDTO.setContactNumber(reviewEntity.getBookingEntity().getUserEntity().getContactNumber());
						userDTO.setEmailId(reviewEntity.getBookingEntity().getUserEntity().getEmailId());
						userDTO.setPassword(reviewEntity.getBookingEntity().getUserEntity().getPassword());
						bookingDTO.setUser(userDTO);
						

						reviewList.add(reviewDTO);	
					}
					if(reviewList.isEmpty())
						throw new WanderLustException("ReviewService.REVIEW_NOT_DONE_BY_USER");
					return reviewList;
					
				}
				
				@Override
				public List<ReviewDTO> viewReview() throws WanderLustException
				{
					Review re=new Review();
					List<Review> review = reviewRepository.findByReview(re);
					List<ReviewDTO> reviewList = new ArrayList<ReviewDTO>();
					for (Review reviewEntity : review) {	
						ReviewDTO reviewDTO=new ReviewDTO();
						reviewDTO.setReviewId(reviewEntity.getReviewId());
						reviewDTO.setReview(reviewEntity.getReview());
						reviewDTO.setRating(reviewEntity.getRating());
						reviewDTO.setUrl(reviewEntity.getUrl());
						reviewDTO.setReview_Date(reviewEntity.getReview_Date());
						
						BookingDTO bookingDTO = new BookingDTO();
						bookingDTO.setBookingId(reviewEntity.getBookingEntity().getBookingId());
						bookingDTO.setCheckIn(reviewEntity.getBookingEntity().getCheckIn());
						bookingDTO.setCheckOut(reviewEntity.getBookingEntity().getCheckOut());
						bookingDTO.setNoOfPeople(reviewEntity.getBookingEntity().getNoOfPeople());
						bookingDTO.setTimeOfBooking(reviewEntity.getBookingEntity().getTimeOfBooking());
						bookingDTO.setTotalCost(reviewEntity.getBookingEntity().getTotalCost());
						reviewDTO.setBooking(bookingDTO);
						
						DestinationDTO destinationDTO = new DestinationDTO();
						destinationDTO.setDestinationId(reviewEntity.getBookingEntity().getDestinationEntity().getDestinationId());
						destinationDTO.setAvailability(reviewEntity.getBookingEntity().getDestinationEntity().getAvailability());
						destinationDTO.setChargePerPerson(reviewEntity.getBookingEntity().getDestinationEntity().getChargePerPerson());
						destinationDTO.setContinent(reviewEntity.getBookingEntity().getDestinationEntity().getContinent());
						destinationDTO.setDestinationName(reviewEntity.getBookingEntity().getDestinationEntity().getDestinationName());
						destinationDTO.setDiscount(reviewEntity.getBookingEntity().getDestinationEntity().getDiscount());
						destinationDTO.setFlightCharge(reviewEntity.getBookingEntity().getDestinationEntity().getFlightCharge());
						destinationDTO.setImageUrl(reviewEntity.getBookingEntity().getDestinationEntity().getImageUrl());
						destinationDTO.setNoOfNights(reviewEntity.getBookingEntity().getDestinationEntity().getNoOfNights());
						destinationDTO.setRating(reviewEntity.getBookingEntity().getDestinationEntity().getRating());
						destinationDTO.setNoOfRating(reviewEntity.getBookingEntity().getDestinationEntity().getNoOfRating());
						bookingDTO.setDestinationDTO(destinationDTO);
							
						DetailsDTO detailsDTO=new DetailsDTO();
						detailsDTO.setAbout(reviewEntity.getBookingEntity().getDestinationEntity().getDetailsEntity().getAbout());
						detailsDTO.setDetailsId(reviewEntity.getBookingEntity().getDestinationEntity().getDetailsEntity().getDetailsId());
						detailsDTO.setHighlights(reviewEntity.getBookingEntity().getDestinationEntity().getDetailsEntity().getHighlights());
						detailsDTO.setPace(reviewEntity.getBookingEntity().getDestinationEntity().getDetailsEntity().getPace());
						detailsDTO.setPackageInclusion(reviewEntity.getBookingEntity().getDestinationEntity().getDetailsEntity().getPackageInclusion());
						destinationDTO.setDetailsDTO(detailsDTO);
						
						ItineraryDTO itineraryDTO=new ItineraryDTO();
						itineraryDTO.setFirstDay(reviewEntity.getBookingEntity().getDestinationEntity().getDetailsEntity().getItineraryEntity().getFirstDay());
						itineraryDTO.setItineraryId(reviewEntity.getBookingEntity().getDestinationEntity().getDetailsEntity().getItineraryEntity().getItineraryId());
						itineraryDTO.setLastDay(reviewEntity.getBookingEntity().getDestinationEntity().getDetailsEntity().getItineraryEntity().getLastDay());
						itineraryDTO.setRestOfDays(reviewEntity.getBookingEntity().getDestinationEntity().getDetailsEntity().getItineraryEntity().getRestOfDays());
						detailsDTO.setItinerary(itineraryDTO);
								
						UserDTO userDTO = new UserDTO();
						userDTO.setUserId(reviewEntity.getBookingEntity().getUserEntity().getUserId());
						userDTO.setUserName(reviewEntity.getBookingEntity().getUserEntity().getUserName());
						userDTO.setContactNumber(reviewEntity.getBookingEntity().getUserEntity().getContactNumber());
						userDTO.setEmailId(reviewEntity.getBookingEntity().getUserEntity().getEmailId());
						userDTO.setPassword(reviewEntity.getBookingEntity().getUserEntity().getPassword());
						bookingDTO.setUser(userDTO);

						
						reviewList.add(reviewDTO);
						
					}
					if(reviewList.isEmpty())
						throw new WanderLustException("ReviewService.NOT_DONE_ANY_RATING");
					return reviewList;
				}
				
				@Override
				public ReviewDTO addReview(ReviewDTO reviewDTO) throws WanderLustException
				{
					Optional<Booking> optional = bookingRepository.findById(reviewDTO.getBooking().getBookingId());
					Booking booking=optional.orElseThrow(() -> new WanderLustException("NO_BOOKINGS_FOUND"));
					Review review = new Review();
						review.setReviewId(reviewDTO.getReviewId());
						review.setReview_Date(reviewDTO.getReview_Date());
						review.setUrl(reviewDTO.getUrl());
						review.setReview(reviewDTO.getReview());
						review.setBookingEntity(booking);
					

					Destination destination= booking.getDestinationEntity();

						if(destination.getRating() == 0) {
							destination.setRating((destination.getRating() + reviewDTO.getRating()));
							}
						else {
							destination.setRating((destination.getRating() + reviewDTO.getRating())/2);
							}
						destination.setNoOfRating(destination.getNoOfRating()+1);
					destinationRepository.save(destination);
					reviewRepository.save(review);
					reviewDTO.setReviewId(review.getReviewId());
					return reviewDTO;
				}
}







