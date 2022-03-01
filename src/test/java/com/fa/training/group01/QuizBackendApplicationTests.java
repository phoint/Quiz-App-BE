package com.fa.training.group01;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fa.training.group01.entity.UserEntity;
import com.fa.training.group01.repository.IUserRepository;
import com.fa.training.group01.service.ICloudinaryService;
import com.fa.training.group01.service.IUserService;
import com.fa.training.group01.service.impl.CloudinaryService;
import com.fa.training.group01.specification.UserSpecification;
import com.fa.training.group01.util.Regex;

@SpringBootTest
class QuizBackendApplicationTests {
	@Autowired
	private Cloudinary cloudinary;

	@Test
	void contextLoads() throws IOException, URISyntaxException {
		System.out.println(Regex.matchesPattern(Regex.NAME_PATTERN, "Vũ Minh Hiếu"));
	}

}
