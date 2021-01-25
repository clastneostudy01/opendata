package com.example.myworkspace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class OpendataApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpendataApplication.class, args);
	}

}
