package com.fa.training.group01;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import com.fa.training.group01.dto.RegisterFormUserDTO;

@SpringBootApplication
@EnableAsync
public class QuizBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(QuizBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}
