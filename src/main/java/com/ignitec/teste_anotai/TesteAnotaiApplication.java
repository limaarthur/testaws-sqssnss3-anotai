package com.ignitec.teste_anotai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class TesteAnotaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesteAnotaiApplication.class, args);
	}

}
