package com.escoger.searches;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.escoger.searches")
public class EscogerSearchesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EscogerSearchesApplication.class, args);
	}

}

