package com.example.Week11SpringRestTemplateAlbum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Week11SpringRestTemplateAlbumApplication {

	public static void main(String[] args) {
		SpringApplication.run(Week11SpringRestTemplateAlbumApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
