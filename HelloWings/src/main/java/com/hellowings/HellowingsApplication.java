package com.hellowings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HellowingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HellowingsApplication.class, args);
	}

}
