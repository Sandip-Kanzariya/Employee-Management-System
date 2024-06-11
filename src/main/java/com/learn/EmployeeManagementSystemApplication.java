package com.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.learn.config.DotenvInitializer;

@SpringBootApplication
public class EmployeeManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(EmployeeManagementSystemApplication.class);
		application.addInitializers(new DotenvInitializer());
		application.run(args);
	}
}
