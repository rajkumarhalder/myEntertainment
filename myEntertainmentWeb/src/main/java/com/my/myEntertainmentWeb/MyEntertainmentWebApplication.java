package com.my.myEntertainmentWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication

@ComponentScan("com.my.")
@EnableAutoConfiguration
@CrossOrigin("http://localhost:4200")
@EnableMongoRepositories("com.my.repository")
public class MyEntertainmentWebApplication {

	public static void main(String[] args) {
	
		SpringApplication.run(MyEntertainmentWebApplication.class, args);
	}
}
