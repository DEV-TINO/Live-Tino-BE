package com.example.live_tino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LiveTinoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiveTinoApplication.class, args);
	}

}