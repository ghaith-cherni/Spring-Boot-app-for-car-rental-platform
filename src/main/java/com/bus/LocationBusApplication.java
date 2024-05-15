package com.bus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class LocationBusApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocationBusApplication.class, args);
	}

}
