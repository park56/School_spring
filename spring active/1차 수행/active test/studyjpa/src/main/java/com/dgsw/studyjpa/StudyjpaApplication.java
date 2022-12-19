package com.dgsw.studyjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class StudyjpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyjpaApplication.class, args);
	}
}
