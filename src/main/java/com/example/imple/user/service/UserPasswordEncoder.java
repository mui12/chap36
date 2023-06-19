package com.example.imple.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

//@Component
@Slf4j
public class UserPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		
		log.info("rawPassword = {}", rawPassword);
		log.info("encodedPassword = {}", encodedPassword);
		
		/* 암호화 했을 때 일치하는지 비교 */
		return encodedPassword.equals(encode(rawPassword));	
	}

}
