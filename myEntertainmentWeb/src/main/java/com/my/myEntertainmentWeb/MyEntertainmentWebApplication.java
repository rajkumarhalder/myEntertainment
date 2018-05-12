package com.my.myEntertainmentWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication

@ComponentScan("com.my.")
@EnableAutoConfiguration
@EnableMongoRepositories("com.my.repository")
public class MyEntertainmentWebApplication {

	public static void main(String[] args) {
	
		SpringApplication.run(MyEntertainmentWebApplication.class, args);
	}
}
