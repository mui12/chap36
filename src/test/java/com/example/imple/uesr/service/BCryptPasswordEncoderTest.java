package com.example.imple.uesr.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderTest {
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Test
	void encode() {
		var encodeStr = encoder.encode("1234");
		System.out.println(encodeStr);
	}
	
	@Test
	void match() {
		var success = encoder.matches("1234", "$2a$10$qVHhayvRFvUvNd7043/mgeJbNj8IpJEa.O0PqLVS8GPIHyyy.NrfC");
		System.out.println(success);
		assertThat(success).isEqualTo(true);
	
		success = encoder.matches("1234", "1234");
		System.out.println(success);	
		assertThat(success).isEqualTo(false);
	
	
	
	}
	
	
	
	
	
}
