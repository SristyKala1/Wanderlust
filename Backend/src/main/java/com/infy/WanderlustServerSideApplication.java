package com.infy;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import com.infy.dto.DestinationDTO;
import com.infy.service.DestinationServiceImpl;

@SpringBootApplication
@PropertySource(value = { "classpath:messages.properties" })
@ComponentScan
public class WanderlustServerSideApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(WanderlustServerSideApplication.class, args);
	}

}
