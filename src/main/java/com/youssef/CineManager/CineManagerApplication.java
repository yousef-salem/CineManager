package com.youssef.CineManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class CineManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CineManagerApplication.class, args);
	}


}
