package com.list.music;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.list.music.service.DataServerInitializer;

@SpringBootApplication
public class MusicApplication {

	@Autowired
	private DataServerInitializer initializer;

	public static void main(String[] args) {
		SpringApplication.run(MusicApplication.class, args);
	}

	@Bean
	public ApplicationRunner initializer() {
		this.initializer.init();
		return null;

	}

}
