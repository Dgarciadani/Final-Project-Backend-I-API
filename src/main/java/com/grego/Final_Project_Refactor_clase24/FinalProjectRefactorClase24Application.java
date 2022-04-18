package com.grego.Final_Project_Refactor_clase24;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FinalProjectRefactorClase24Application {

	@Bean
	public ModelMapper modelmapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(FinalProjectRefactorClase24Application.class, args);
	}

}
