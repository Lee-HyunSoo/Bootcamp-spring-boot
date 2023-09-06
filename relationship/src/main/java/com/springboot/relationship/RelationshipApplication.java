package com.springboot.relationship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RelationshipApplication {

	public static void main(String[] args) {
		SpringApplication.run(RelationshipApplication.class, args);
	}

}
