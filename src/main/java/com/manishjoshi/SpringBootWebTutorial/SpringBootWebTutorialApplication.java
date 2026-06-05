package com.manishjoshi.SpringBootWebTutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootWebTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebTutorialApplication.class, args);
	}

}
