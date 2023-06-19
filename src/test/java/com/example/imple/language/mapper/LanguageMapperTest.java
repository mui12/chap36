package com.example.imple.language.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.imple.salgrade.mapper.SalgradeMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class LanguageMapperTest {
	@Autowired
	LanguageMapper mapper;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	void countAll() {
		var cnt = mapper.countAll();
		System.out.println(cnt);
		assertThat(cnt).isSameAs(984);
	}
	
	
	@Test
	void selectAll() throws IOException {
		var list = mapper.selectAll();
		System.out.println(list);
		
		objectMapper.createGenerator(System.out)
		.useDefaultPrettyPrinter()
		.writeObject(list);
	}
	
}
