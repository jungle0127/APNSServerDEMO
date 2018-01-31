package com.ps.apns;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.ps.apns.dao.domain")
public class ApnsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApnsApplication.class, args);
	}
}
