package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class SecurityConfig1 {
	
	@Bean  // spring이 기동하면서 호출 
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> {			// 보안관련 (Customizer 선택
			csrf.disable();
		});
		http.cors(cors -> {
			cors.disable();
		});
		
		http.authorizeHttpRequests(request -> {    // 로그인을 하지 않아도 request로 들어오는 모든 요구에 대해 허용
			request.anyRequest().permitAll();
		});
		
		
		return http.build();
	}
}
