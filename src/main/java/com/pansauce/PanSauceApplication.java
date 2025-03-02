package com.pansauce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PanSauceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PanSauceApplication.class, args);
	}

}
