package com.example.sheep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.sheep.infraestructure.driver_adapters.jpa_repository"})
public class UsuarioApplication {

	public static void main(String[] args) {SpringApplication.run(UsuarioApplication.class, args);
	}

}
