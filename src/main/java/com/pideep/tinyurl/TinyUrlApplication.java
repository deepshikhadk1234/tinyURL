package com.pideep.tinyurl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TinyUrlApplication {

	public static void main(String[] args) {
		System.out.println("Hello World");
		SpringApplication.run(TinyUrlApplication.class, args);
	}

}
