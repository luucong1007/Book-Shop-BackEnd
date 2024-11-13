package com.mshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BookShopApplication {

	private final String HelloString = "Hello World!";

	public static void main(String[] args) {
		SpringApplication.run(BookShopApplication.class, args);
	}

}
