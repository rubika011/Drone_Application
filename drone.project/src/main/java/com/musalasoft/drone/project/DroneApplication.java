package com.musalasoft.drone.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DroneApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(DroneApplication.class, args);

	}

}
