package com.my.myEntertainmentWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

@ComponentScan("com.my.")
@EnableJpaRepositories("com.my.repository")
@EntityScan("com.my.model")
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class MyEntertainmentWebApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
	
		SpringApplication.run(MyEntertainmentWebApplication.class, args);
		
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(MyEntertainmentWebApplication.class);
	}
	
}
