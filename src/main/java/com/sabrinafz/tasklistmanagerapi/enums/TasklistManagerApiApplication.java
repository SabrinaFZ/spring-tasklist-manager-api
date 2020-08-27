package com.sabrinafz.tasklistmanagerapi.enums;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TasklistManagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasklistManagerApiApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}

}
