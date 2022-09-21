package com.infy.repository;
import java.util.List;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.infy.entity.Destination;
public interface DestinationRepository extends CrudRepository<Destination , Integer>{
	
	public List<Destination> findByContinentIgnoreCase( String continent);
	public List<Destination> findByDiscountGreaterThan( Float dicountGreater);
	public Destination findByDestinationId(String destinationId);
	
}
