package com.example.sheep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.sheep")
public class GanaderoServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GanaderoServiceApplication.class, args);
    }
}
