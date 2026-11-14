package com.app.movie;

import org.springframework.boot.SpringApplication;

public class TestMovieApplication {

	public static void main(String[] args) {
		SpringApplication.from(MovieApplication::main)
				.with(TestcontainersConfiguration.class)
				.run(args);
	}

}
