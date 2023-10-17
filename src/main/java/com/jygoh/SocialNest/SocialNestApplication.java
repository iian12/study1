package com.jygoh.SocialNest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SocialNestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialNestApplication.class, args);
	}

}
