package com.m2n.bookshelf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories({"com.m2n.bookshelf.repository"})
public class Lab52Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab52Application.class, args);
	}

}
