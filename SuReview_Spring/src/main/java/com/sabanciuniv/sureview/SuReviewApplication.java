package com.sabanciuniv.sureview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SuReviewApplication implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(SuReviewApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SuReviewApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Testing the application...");
	}
}
