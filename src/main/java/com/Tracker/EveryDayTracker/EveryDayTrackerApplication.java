package com.Tracker.EveryDayTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.Tracker.EveryDayTracker.entity")
public class EveryDayTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EveryDayTrackerApplication.class, args);
	}

}
