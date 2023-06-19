package com.example.imple.salgrade.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class SalgradeMapperTest {

	@Autowired
	SalgradeMapper mapper;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	void countAll() {
		var cnt = mapper.countAll();
		System.out.println(cnt);
	}
	
}
