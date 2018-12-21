package com.my.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class EntertainmentDBConfig {
	
	@Autowired
	private Environment environment;
	
	@Bean(name="dataSource")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("entertainment.datasource.driver-class-name"));
		dataSource.setUrl(environment.getProperty("entertainment.datasource.url"));
		dataSource.setUsername(environment.getProperty("entertainment.datasource.username"));
		dataSource.setPassword(environment.getProperty("entertainment.datasource.password"));
		return dataSource;
	}

}
