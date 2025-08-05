package com.vishal.service_offring_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServiceOffringServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceOffringServiceApplication.class, args);
	}

}
