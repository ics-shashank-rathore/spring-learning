package com.learning.spring.mysqlzoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(
		scanBasePackages = {
				"com.learning.spring.mysqlsecurity", 
				"com.learning.spring.mysqlzoo"},
		exclude = SecurityAutoConfiguration.class
		)
public class ZooMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZooMysqlApplication.class, args);
	}

}
