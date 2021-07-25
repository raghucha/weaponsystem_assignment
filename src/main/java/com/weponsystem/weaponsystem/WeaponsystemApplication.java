package com.weponsystem.weaponsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WeaponsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeaponsystemApplication.class, args);
	}

}
