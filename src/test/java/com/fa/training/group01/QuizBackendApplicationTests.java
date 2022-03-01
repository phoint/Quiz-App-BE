package com.fa.training.group01;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fa.training.group01.util.Regex;

@SpringBootTest
class QuizBackendApplicationTests {

	@Test
	void contextLoads() throws IOException, URISyntaxException {
		System.out.println(Regex.matchesPattern(Regex.NAME_PATTERN, "Vũ Minh Hiếu"));
	}

}
