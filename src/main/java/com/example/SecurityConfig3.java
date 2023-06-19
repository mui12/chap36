
package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class SecurityConfig3 {
	
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
			request.requestMatchers("/", "/error").permitAll();   // 인증이 필요없는 url 설정
			request.requestMatchers("/webjars/**").permitAll();   // webjars이하 url는 모두 허용  
			
			request.requestMatchers("/dept/list", "/dept/detail/{key}").permitAll();
			request.requestMatchers("/emp/list", "/emp/detail/{key}").permitAll();
			request.requestMatchers("/salgrade/list", "/salgrade/detail/{key}").permitAll();
			
			
			request.anyRequest().authenticated();  // 모든 log에 대해서 인증 필요 
//			request.anyRequest().permitAll();      // 로그인을 하지 않아도 request로 들어오는 모든 요구에 대해 허용
		});
		
		http.formLogin(login -> {
			login.loginPage("/user/login");
			
			/* 로그인 성공 처리 with UserLoginController*/
			login.defaultSuccessUrl("/", true); // 로그인이 성공하면 항상(true) "/"루트로 가라 
			
			/* 실패시 에러 처리 with UserLoginController*/
			login.failureHandler((request, response, e) -> {
				request.setAttribute("exception", e);
				request.getRequestDispatcher("/user/login-fail").forward(request, response);
				
				/* 실패하면 e가 exception이름으로 /user/login-fail로 forward되는데 
				   controller에서 fail로 post로 받아서 login으로 리턴하게 된다. */
				
				
			});
			
			login.permitAll();
		});
	
		http.logout(logout -> {
			logout.logoutUrl("/user/logout");
			logout.permitAll();
		});
		
		
		
		return http.build();
	}
}
