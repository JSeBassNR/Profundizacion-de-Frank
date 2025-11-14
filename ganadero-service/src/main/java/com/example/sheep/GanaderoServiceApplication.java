package com.example.ganadero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ecomerce.producto")
public class GanaderoServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GanaderoServiceApplication.class, args);
    }
}
