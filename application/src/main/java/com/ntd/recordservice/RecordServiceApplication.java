package com.ntd.recordservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("com.ntd.recordservice")
@SpringBootApplication
@EnableJpaRepositories("com.ntd.recordservice")
@EntityScan("com.ntd.recordservice")
public class RecordServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecordServiceApplication.class, args);
	}

}
