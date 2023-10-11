package com.merchant.solutions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SolutionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolutionsApplication.class, args);
	}

}
