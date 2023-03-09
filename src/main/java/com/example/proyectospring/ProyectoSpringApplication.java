package com.example.proyectospring;

import com.example.proyectospring.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class ProyectoSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoSpringApplication.class, args);
    }

}
