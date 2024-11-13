package com.mshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BookShopApplication {

	public static void main(String[] args) {
		String HelloString = "Hello World!";
		System.out.println(HelloString);
		SpringApplication.run(BookShopApplication.class, args);
	}

}
