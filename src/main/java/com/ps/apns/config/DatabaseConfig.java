package com.ps.apns.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DatabaseConfig {
	@Bean(name="primaryDatasource")
	@Primary
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource getDataSource() {
		return DataSourceBuilder.create().build();
	}
}
