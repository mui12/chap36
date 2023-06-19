package com.example.standard.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;

public interface DetailController<T> {
	
	@GetMapping("/detail/{key}")
	String detail(@PathVariable T key, Model model, HttpServletRequest request);
	// view return하게 void → String 
		
	@GetMapping("/detail/{key1}/{key2}")  //PK가 두개
	default String detail(@PathVariable T key1, @PathVariable T key2, Model model, HttpServletRequest request) {
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not Implementation");
		// {key1}/{key2}를 직접적으로 호출하려고 하면, 응답으로 BAD_REQUEST 400 error로 "Not Implementation" 문구 뜸
	}
	// default : 선별적 (해도되고 안해도되고)
}
