package com.example.testetecnico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@EnableMethodSecurity
@SpringBootApplication(scanBasePackages = {"com.example.testetecnico"})
public class TesteTecnicoApplication {
    public static void main(String[] args) {
        SpringApplication.run(TesteTecnicoApplication.class, args);
    }

}
