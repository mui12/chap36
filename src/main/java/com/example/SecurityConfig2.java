
package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class SecurityConfig2 {
	
	@Bean  // spring이 기동하면서 호출 
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> {			// 보안관련 (Customizer 선택
			csrf.disable();
		});
		http.cors(cors -> {
			cors.disable();
		});
		
		http.authorizeHttpRequests(request -> {    
			request.dispatcherTypeMatchers(jakarta.servlet.DispatcherType.FORWARD).permitAll();
			request.requestMatchers("/").permitAll();   // 인증이 필요없는 url 설정
			
			request.anyRequest().authenticated();  // 모든 log에 대해서 인증 필요 
//			request.anyRequest().permitAll();      // 로그인을 하지 않아도 request로 들어오는 모든 요구에 대해 허용
		});
		
		http.formLogin(login -> {
			login.loginPage("/user/login");
			login.permitAll();
		});
	
		http.logout(logout -> {
			logout.logoutUrl("/user/logout");
			logout.permitAll();
		});
		
		
		
		return http.build();
	}
}
