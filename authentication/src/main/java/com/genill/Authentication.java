package com.genill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class Authentication {

	public static void main(String[] args) {
		SpringApplication.run(Authentication.class, args);
	}
}
